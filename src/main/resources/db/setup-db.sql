create database dronedb;
create user if not exists 'drone_user'@'localhost' identified by 'Drone123';
grant all privileges on dronedb.* to 'drone_user'@'localhost';
flush privileges;