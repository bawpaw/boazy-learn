[TOC]

#   harbor 安装

* harbor 包含许多镜像，所以要采用 `Docker Compose` 方式安装

## 查找 harbor 版

```
https://github.com/goharbor/harbor/releases/
```

* 选择 `1.9.3` 版本

## 安装 harbor

### 创建数据目录

```bash
mkdir -p /boazy/data/dockerdata/harbor
```

### 切换目录

```bash
cd /boazy/data/dockerdata/harbor
```

### 下载 harbor

```bash
# 如果提示找不到 wget 命令，请通过 yum instal wget 安装
wget https://github.com/vmware/harbor/releases/download/v1.9.3/harbor-online-installer-v1.9.3.tgz
```

### 解压下载的文件

* 解压文件并将解压出来的 harbor 目录重命名为 harbor_install

```bash
tar xvf harbor-online-installer-v1.9.3.tgz && mv harbor harbor_install
```

* 执行结果

```bash
[root@centos7-qscft harbor]# tar xvf harbor-online-installer-v1.9.3.tgz && mv harbor harbor_install
harbor/prepare
harbor/LICENSE
harbor/install.sh
harbor/harbor.yml
[root@centos7-qscft harbor]#
```

* 查看目录

```bash
ll -h
```

```bash
[root@centos7-qscft harbor]# ll -h
total 12K
drwxr-xr-x. 2 root root   72 Dec 12 17:15 harbor_install
-rw-r--r--. 1 root root 8.2K Dec 12 17:13 harbor-online-installer-v1.9.3.tgz
[root@centos7-qscft harbor]#
```

### 切换到解压的目录

```bash
cd /boazy/data/dockerdata/harbor/harbor_install
```

* 查看目录下的文件

```bash
[root@centos7-qscft harbor_install]# ll -h
total 32K
-rw-r--r--. 1 root root 5.7K Nov 18 16:37 harbor.yml
-rwxr-xr-x. 1 root root 5.0K Nov 18 16:37 install.sh
-rw-r--r--. 1 root root  12K Nov 18 16:37 LICENSE
-rwxr-xr-x. 1 root root 1.8K Nov 18 16:37 prepare
[root@centos7-qscft harbor_install]#
```

### 修改 harbor.yml 文件的属性值

* 修改前备份文件

```bash
cp harbor.yml harbor.yml.bak191212
```

* 查看系统的 hostname

```bash
hostnamectl
```

* 修改文件属性值

```bash
# 对应的属性值如下调整（根据自身情况设置值）
hostname=centos7-qscft
http.port=8082
harbor_admin_password=boazy@.harbor
database.password=boazy@.harbor.db
data_volume=/boazy/data/dockerdata/harbor/data
log.local.location=/boazy/data/dockerdata/harbor/log
```

### 先执行 `./prepare` 

```bash
./prepare
```

* 这个命令会生成 `docker-compose.yml` 文件和 `common` 目录

```bash
[root@centos7-qscft harbor_install]# ll -h
total 40K
drwxr-xr-x. 3 root root   20 Dec 12 17:36 common
-rw-r--r--. 1 root root 5.5K Dec 12 17:36 docker-compose.yml
-rw-r--r--. 1 root root 5.8K Dec 12 17:36 harbor.yml
-rwxr-xr-x. 1 root root 5.0K Nov 18 16:37 install.sh
-rw-r--r--. 1 root root  12K Nov 18 16:37 LICENSE
-rwxr-xr-x. 1 root root 1.8K Nov 18 16:37 prepare
```

### 再执行 `./install.sh` 安装 harbor

*  `./install.sh`  会根据 docker-compose.yml 文件内容进行安装（拉取相关镜像及运行相关镜像）

```bash
./install.sh
```

* 结果成功（第一次会 pull 相关镜像的）

```bash
[root@centos7-qscft harbor_install]# ./install.sh

[Step 0]: checking installation environment ...

Note: docker version: 19.03.5

Note: docker-compose version: 1.25.0


[Step 1]: preparing environment ...
prepare base dir is set to /boazy/data/dockerdata/harbor/harbor_install
Clearing the configuration file: /config/log/logrotate.conf
Clearing the configuration file: /config/log/rsyslog_docker.conf
Clearing the configuration file: /config/nginx/nginx.conf
Clearing the configuration file: /config/core/env
Clearing the configuration file: /config/core/app.conf
Clearing the configuration file: /config/registry/config.yml
Clearing the configuration file: /config/registryctl/env
Clearing the configuration file: /config/registryctl/config.yml
Clearing the configuration file: /config/db/env
Clearing the configuration file: /config/jobservice/env
Clearing the configuration file: /config/jobservice/config.yml
Generated configuration file: /config/log/logrotate.conf
Generated configuration file: /config/log/rsyslog_docker.conf
Generated configuration file: /config/nginx/nginx.conf
Generated configuration file: /config/core/env
Generated configuration file: /config/core/app.conf
Generated configuration file: /config/registry/config.yml
Generated configuration file: /config/registryctl/env
Generated configuration file: /config/db/env
Generated configuration file: /config/jobservice/env
Generated configuration file: /config/jobservice/config.yml
loaded secret from file: /secret/keys/secretkey
Generated configuration file: /compose_location/docker-compose.yml
Clean up the input dir



[Step 2]: starting Harbor ...
Creating network "harbor_install_harbor" with the default driver
Creating harbor-log ... done
Creating harbor-db     ... done
Creating harbor-portal ... done
Creating redis         ... done
Creating registry      ... done
Creating registryctl   ... done
Creating harbor-core   ... done
Creating nginx             ... done
Creating harbor-jobservice ... done

✔ ----Harbor has been installed and started successfully.----

Now you should be able to visit the admin portal at http://centos7-qscft.
For more details, please visit https://github.com/goharbor/harbor .

[root@centos7-qscft harbor_install]# 
```

* 访问一下地址（成功访问）

```bash
[root@centos7-qscft harbor]# curl http://centos7-qscft:8082
<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <title>Harbor</title>
    <base href="/">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" type="image/x-icon" href="favicon.ico?v=2">
<link rel="stylesheet" href="styles.39a196eca66863018f0c.css"></head>

<body>
    <harbor-app>
        <div class="spinner spinner-lg app-loading">
            Loading...
        </div>
    </harbor-app>
<script type="text/javascript" src="runtime.26209474bfa8dc87a77c.js"></script><script type="text/javascript" src="scripts.3f50235634498feee121.js"></script><script type="text/javascript" src="main.29ba60722e88a57ea9b5.js"></script></body>

</html>[root@centos7-qscft harbor]#
```

