#install psql and set correct rights
sudo mkdir /var/lib/pgsql &&
sudo chown -R postgres:postgres /var/lib/pgsql &&
sudo apt install postgresql-server &&
sudo /usr/bin/postgresql-setup --initdb &&

#create database directory with correct rights
sudo mkdir /data/trade_db &&
sudo chown -R postgres:postgres /data/trade_db &&
sudo mkdir /data/tradeTest_db &&
sudo chown -R postgres:postgres /data/tradeTest_db &&
sudo chcon unconfined_u:object_r:postgresql_db_t:s0 /data &&
sudo chcon unconfined_u:object_r:postgresql_db_t:s0 /data/tradedb &&
sudo systemctl enable postgresql &&
sudo systemctl start postgresql &&

#set validation
sudo -u postgres psql; &&
ALTER ROLE postgres WITH PASSWORD '1551';
exit;
sudo nano /var/lib/pgsql/data/pg_hba.conf

##fedora - disable selinux
#sudo setenforce 0 &&
#sudo nano /etc/selinux/config &&
##SELINUX=disabled
#pause &&
