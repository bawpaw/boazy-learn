# 安装 Docker

## 卸载

* 之前环境没有安装过 Docker 跳过

```bash
yum remove docker \
    docker-client \
    docker-client-latest \
    docker-common \
    docker-latest \
    docker-latest-logrotate \
    docker-logrotate \
    docker-engine \
    docker-ce-cli
```

## 安装

### 安装依懒

```bash
yum install -y yum-utils \
	device-mapper-persistent-data \
	lvm2
```

### 设置 Docker 仓库

* 以下任选一个（选择国内的地址速度会快）

```bash
# 
yum-config-manager \
	--add-repo \
	https://mydream.ink/utils/container/docker-ce.repo
# 中科大
yum-config-manager \
	--add-repo \
	https://mirrors.ustc.edu.cn/docker-ce/linux/centos/docker-ce.repo
# 官方
yum-config-manager \
    --add-repo \
    https://download.docker.com/linux/centos/docker-ce.repo
```

* 如果设置失败，可能是系统时间不对，执行以下命令同步时间并设置
  
  * 错误代码
  
    ```bash
    Loaded plugins: fastestmirror, langpacks
    adding repo from: https://mirrors.ustc.edu.cn/docker-ce/linux/centos/docker-ce.repo
    grabbing file https://mirrors.ustc.edu.cn/docker-ce/linux/centos/docker-ce.repo to /etc/yum.repos.d/docker-ce.repo
    Could not fetch/save url https://mirrors.ustc.edu.cn/docker-ce/linux/centos/docker-ce.repo to file /etc/yum.repos.d/docker-ce.repo: [Errno 14] curl#60 - "Peer's Certificate has expired."
    ```
  
  * 错误解决
  
    ```bash
    ntpdate pool.ntp.org
    hwclock -w
    ```

### 更新 `yum` 软件源缓存

```bash
yum makecache fast
```

### 安装 Docker

```bash
yum install docker-ce \
    docker-ce-cli \
    containerd.io
```

### 启动 Docker

```bash
# 启动 docker
systemctl start docker
# 设置 docker 开启启动
systemctl enable docker
```

### 配置镜像加速

```bash
vi /etc/docker/daemon.json
```

```bash
{
  "registry-mirrors": [
    "https://dockerhub.azk8s.cn",
    "https://reg-mirror.qiniu.com"
  ]
}
```

```bash
# 重新加载 daemon 配置
systemctl daemon-reload
# 重启 docker
systemctl restart docker
```



## 安装问题

### 问题1（设置 Docker 仓库）

* 错误代码

```bash
Could not fetch/save url https://download.docker.com/linux/centos/docker-ce.repo to file /etc/yum.repos.d/docker-ce.repo: [Errno 14] curl#60 - "Peer's Certificate has expired."
```

* 问题解决（同步时间）

```bash
ntpdate pool.ntp.org
hwclock -w
```

### 问题2（搜索镜像）

* 错误代码

```bash
Error response from daemon: Get https://index.docker.io/v1/search?q=redis&n=25: x509: certificate has expired or is not yet valid^C
```

* 问题解决（配置镜像加速）

* 问题解决（同步时间）

```bash
ntpdate pool.ntp.org
hwclock -w
```



# 镜像安装运行

## gitlab/gitlab-ce

* https://juejin.im/post/5cc1df885188252d6c43fd91

```bash
docker run -d \
    --hostname boazy.f3322.net \
    -P -p 444:443 -p 88:80 -p 23:22 \
    --name gitlab \
    --restart always \
    -v /home/boazy/dockerdata/gitlab/config:/etc/gitlab \
    -v /home/boazy/dockerdata/gitlab/logs:/var/log/gitlab \
    -v /home/boazy/dockerdata/gitlab/data:/var/opt/gitlab \
    -v /etc/localtime:/etc/localtime:ro \
    gitlab/gitlab-ce:latest
```

### 错误1（启动不了）

* 错误代码

```bash
/opt/gitlab/embedded/bin/runsvdir-start: line 24: ulimit: pending signals: cannot modify limit: Operation not permitted
/opt/gitlab/embedded/bin/runsvdir-start: line 37: /proc/sys/fs/file-max: Read-only file system
```

* 问题解决

```bash
chown -R 200 /home/boazy/dockerdata/gitlab/
```

## sonatype/nexus3

```bash
docker run -d -p 8081:8081 \
	--restart=always \
	--name nexus \
	-v /home/boazy/dockerdata/nexus-data:/nexus-data \
	-e INSTALL4J_ADD_VM_PARAMS="-Duser.timezone=Asia/Shanghai" \
	sonatype/nexus3:latest
```

### 错误1（启动不了）

- 错误代码

```bash

```

- 问题解决

```bash
chown -R 200 /home/boazy/dockerdata/nexus-data/
```
