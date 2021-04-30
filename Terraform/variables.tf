variable "flavor" { default = "m1.medium" }
variable "image" { default = "Debian Buster 10.3.0" }
#variable "instance" { default = "tf_instance" }

variable "name" { default = "server" }
variable "name2" { default = "Debian_Server_2" }

variable "network" { default = "danielNetwork" }   # you need to change this

variable "keypair" { default = "danielKey2" }       # you need to change this
variable "pool" { default = "cscloud_private_floating" }
variable "server_script2" { default = "./server.sh" }
variable "security_description" { default = "Terraform security group" }
variable "security_name" { default = "default_Projecters" }
