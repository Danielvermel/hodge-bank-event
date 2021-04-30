#!/bin/bash
#su debian #this will not work - as demonstrated below.
#cd /home/debian
whoami
echo logged in as $USER.
echo in directory $PWD

sudo apt-get update
sudo apt-get install git -y
sudo apt-get install wget -y
sudo apt-get install unzip -y


echo "installing gitlab server key..."
sudo touch ~/.ssh/known_hosts
sudo ssh-keyscan gitlab.cs.cf.ac.uk >> ~/.ssh/known_hosts
sudo chmod 644 ~/.ssh/known_hosts


echo "installing gitlab deployment key..."
touch username_keypair.key
cat << `EOF` >> username_keypair.key
-----BEGIN RSA PRIVATE KEY-----
MIIEogIBAAKCAQEA026iGxMJ0N6VH93P7nBZzN7WJVOtDGQSGLYZ21ruI6zgbrZn
DCTwpl8XEbmDApUh0gei/qzWsZaf7z8oDF4SAe0aCzCbkykHOyQYsoWjaMyXJ7Mb
wLFbxhFXtFJfTt5+ZHJu55uZ/iWwrizsR3zaSinnSgE2lanHaQEdejm4vQgcc57V
9wEiSuv3vyOLf6ic8iJ1oKw+eQhjoIOfQlQSmdAgDIV9qOLYiXmWymZ/7OOH8XyQ
IUU5GILm82k7IOaWrBof7RGfUyPAAcTMfN6PyMgO3BEAZDhf6XNQfNQMoueP6OvP
JefdKIsWNwwEWyXptkRpXsGHG6yfVskoV3mAxwIDAQABAoIBAFwzO4e49qfZ2Ifr
73fYEKDxb8sZQTZNEUMrbf0lGwH9qmU9L5SxKywx/VpiXooqxHPVlrOeZYVN3PK0
xN8XypyCa8ADc2RqGNgmdoqrEkZqKWRLi8es0AuAlpFw9lTrW4ysBkcDqNSWVQSv
XF4USRDcUxsHimkbf3T/4n3hW/k6G4tVfiwkcDtfU4mbo/nfGKgKu2qWTq2kz7RG
yMWQgOQchkIyGrdjJxqNsvmhmZ0DEDJRIJz15EZNxan/MMfnfxqPJuRnSQj+m9ua
UFZeninSsjqV5yMV4ZFlcjziQgTYKPuG7s76bjSrrS/ULii0K5NNHCyo03DN72iV
LhPg6zkCgYEA/oewl63Wy3GM5LXl202oCNByTq9ZEDcPvo/+0oeJUPekotPuZvQo
kpJbB7zH5YbFrcx0otCuNOuo/yQ+lTYg4xwtFQl2EAB1Q4YMpvQMBxaPfB+rKVZ+
5lkOoGkU5pwxzUo+WgYlrwsJE0IIfrsWBYVD7hlLCcy82j8ArSwnOQUCgYEA1Kc5
rg2p0iJ16sf3FDrA0o5Zfe+wKtz6hdQYXgKhNPLNflNKJLqtOgJvQySwccwlmT5F
SZGQYrQKDZOgZen7RJg6NVx2XSYX/ZHaIeQ8QYz+jd6R6f/Es626uqTo1pSB82x9
HoEt9ObphKizUMRvoMNdBLy07qS/TqaJGZosDFsCgYABlqUyfSI/CMlhUAVMNhLb
YzmjZhK/OCi//rQPaksgFzuOzJXLTLDIIWOyC6qPsySergz4SycbK3FVjktZsQnJ
gQ6C+Bklhms07aw8in9ct/ZTRvtRebm3BE1EEQPRYJ4q8tCqzSkFC6OV3C6jY9WC
WU+QN9YxDHcNQaFmc0UXZQKBgF/2DntSwx2GW3vVPNQuJzFc91lzPiY5jfuyWESk
HycLolutLjog22bgDmFYPx87JoReq1mljGheImM6CRm0WqT7pNjLnsYuGIletfax
TdeuzngqxeG4f4UtkqQDIuciIPo+LMAFPz3SrfIlRrXdzxnWzzUCCwvd4E6XrCqX
feUdAoGAYpaWHJPZpeJFFj5qOTcfjvUgjqma3JuguxOiGHKiywRqvQo51N9IfoXq
ByR41JyaGN++Mi/9OP5/row9DHeF4NZRSa/c6Dl334Bvx5B2SR7eOkMMrERoD6hY
tr1sB5FzJ63RjHAFUs+/s1NMiVc9J7vuih3MZgS8EalzUixjrIg=
-----END RSA PRIVATE KEY-----
`EOF`
chmod 400 username_keypair.key

echo "cloning repository..."
ssh-agent bash -c 'ssh-add username_keypair.key; git clone git@gitlab.cs.cf.ac.uk:c1955887/devops_takeaway.git'


echo "Installing Java 11..."
#installink Java
sudo apt install curl -y
curl -O https://download.java.net/java/GA/jdk11/9/GPL/openjdk-11.0.2_linux-x64_bin.tar.gz
tar zxvf openjdk-11.0.2_linux-x64_bin.tar.gz
sudo mv jdk-11.0.2 /usr/local/
export JAVA_HOME=/usr/local/jdk-11.0.2
export PATH=$PATH:$JAVA_HOME/bin

echo "Installing Gradle"
#installing gradle
wget https://services.gradle.org/distributions/gradle-5.4.1-bin.zip
sudo mkdir /opt/gradle
sudo unzip -d /opt/gradle gradle-5.4.1-bin.zip
export PATH=$PATH:/opt/gradle/gradle-5.4.1/bin
echo"gradle version"
gradle -v

echo "Installing MariaDB and Mysql"
#installing Mysql and MariaDB server
sudo apt update
sudo apt install mysql-server -y
sudo apt install mariadb-server -y
sudo systemctl status mariadb
sudo systemctl enable mariadb  # what is this line for

echo 'Entering the project'
pwd
ls | xargs
cd /devops_takeaway

sudo apt install mysql-server -y

echo "Creating mysql user"
sudo mysql -u root  -e "create user 'daniel'@'localhost' identified by 'comsc';"
sudo mysql -u root  -e "grant all privileges on *.* to 'daniel'@'localhost';"

echo 'RUNNING SQL HERE'
mysql -u daniel -pcomsc < schema.sql
mysql -u daniel -pcomsc < data.sql


echo "gradle - actions (clean,bluid,bootrun)"
gradle clean
gradle build
gradle bootrun
