# Docker Desktop for Windows Experience

[TOC]

## 安装 Docker CE 之前

### 试验目标

* 安装 Docker CE 并配置镜像加速，并能正常运行
* 启动 Docker 中的 Kubernetes，并能正常运行
* 配置 Kubernetes Dashboard，并能正常运行
* 了解熟悉过程、记录过程

### 系统要求

[Docker Desktop for Windows](https://docs.docker.com/docker-for-windows/install/) 支持 64 位版本的 Windows 10 Pro，且必须[开启 Hyper-V](https://segmentfault.com/a/1190000012288976)。

### 下载 Docker CE

点击以下链接下载 [Stable](https://download.docker.com/win/stable/Docker for Windows Installer.exe) 或 [Edge](https://download.docker.com/win/edge/Docker Desktop Installer.exe) 版本的 [Docker Desktop for Windows](https://hub.docker.com/editions/community/docker-ce-desktop-windows)。

![](.\images\get-docker-desktop-for-windows.png)

***本文以下载 Stable 版本为例。***

## 安装 Docker CE

* 下载好之后双击 `Docker for Windows Installer.exe` 开始安装。

* 安装好后，查看 `About Docker Desktop`

![About Docker Desktop](.\images\about-docker-dektop.png)

## 配置镜像加速

如果在使用过程中发现拉取 Docker 镜像十分缓慢，可以配置 [Docker 国内镜像加速](https://segmentfault.com/a/1190000019115546)。

* Azure 中国镜像 `https://dockerhub.azk8s.cn`

* 七牛云镜像 `https://reg-mirror.qiniu.com`

* 中科大镜像 `https://docker.mirrors.ustc.edu.cn`

![](.\images\settings-daemon.png)

![](.\images\settings-daemon-advanced.png)

```json
{
  "registry-mirrors": [
    "https://dockerhub.azk8s.cn",
    "https://reg-mirror.qiniu.com",
    "https://docker.mirrors.ustc.edu.cn"
  ],
  "insecure-registries": [],
  "debug": true,
  "experimental": false
}
```

## 启用 Docker 中的 Kubernetes

### 安装 kubectl 命令

* 通过 Docker 的 `About Docker Desktop` 查看 kubernetes 的版本

  通过查看版本为 `kubernetes:v1.14.6` 。

* 下载 `kubernetes:v1.14.6` [kubernetes-client-windows-amd64.tar.gz](https://dl.k8s.io/v1.14.6/kubernetes-client-windows-amd64.tar.gz) （这需要科学上网）

  其他的版本下可通过 https://github.com/kubernetes/kubernetes 中相应版本的 `CHANGELOG-1.XX.md` 文件获取。

* 解压 kubernetes-client-windows-amd64.tar.gz 

  这里解压后目录为 `D:\Local\kubernetes\client\bin` 

![](.\images\kubectl-dir.png)

* 将 `kubectl` 加入到 `系统环境变量PATH` 中

  ![](.\images\kubectl-home&path.png)

* 执行 `kubectl` 命令检查是否OK

  ```bash
  kubectl version
  ```

  ![](.\images\kubectl-version.png)

### 安装 kubernetes 镜像

* 可先参考 https://github.com/AliyunContainerService/k8s-for-docker-desktop 文档

* 通过 [AliyunContainerService/k8s-for-docker-desktop](https://github.com/AliyunContainerService/k8s-for-docker-desktop) 项目手工加载镜像，通过以下命令克隆项目

  (提示：会在执行命令的当前目录下创建 k8s-for-docker-desktop 目录)

  ```bash
  git clone https://github.com/AliyunContainerService/k8s-for-docker-desktop
  ```

* 切换到 k8s-for-docker-desktop 目录，执行 `.\load_images.ps1` 命令（使用 PowerShell）

  等镜像文件 pull 完后，就可以激活 Docker 中的 Kubernetes 了。

  ```bash
  .\load_images.ps1
  ```

  *这里 pull 的 Kubernetes 镜像为 1.14.6*

  **注意，PowerShell 执行命令时可能出现以下错误：**

  ```bash
  .\load_images.ps1 : 无法加载文件 D:\IdeaProjects\k8s-for-docker-desktop\load_images.ps1，因为在此系统上禁止运行脚本。
  ```

  执行以下[命令](https://blog.csdn.net/caijunfen/article/details/78521514)解决错误：

  ```bash
  set-executionpolicy remotesigned
  ```

* Docker 中激活 Kubernetes

  ![](.\images\docker-enable-kubernetes.png)

## 配置 Kubernetes Dashboard

### 可先参考的文档

* https://github.com/AliyunContainerService/k8s-for-docker-desktop 

* https://github.com/kubernetes/dashboard

### 切换 Kubernetes 运行上下文

切换 Kubernetes 运行上下文至 `docker--desktop`

```bash
kubectl config use-context docker-desktop
```

### 验证 Kubernetes 集群状态

```bash
kubectl cluster-info
kubectl get nodes
```

### 部署 Kubernetes dashboard

```bash
kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v1.10.1/src/deploy/recommended/kubernetes-dashboard.yaml
```

### 开启 API Server 访问代理

```bash
kubectl proxy
```

![](.\images\kubectl-proxy.png)

### 访问 Kubernetes dashboard

* http://localhost:8001/api/v1/namespaces/kube-system/services/https:kubernetes-dashboard:/proxy/#!/overview?namespace=default

* http://localhost:8001/api/v1/namespaces/kube-system/services/https:kubernetes-dashboard:/proxy/

### 配置 kubeconfig (可跳过)

* 给配置 `C:\Users\XXX\.kube\config` 文件 `docker-desktop` 设置  `token`

  ```bash
  $TOKEN=((kubectl -n kube-system describe secret default | Select-String "token:") -split " +")[1]
  kubectl config set-credentials docker-desktop --token="${TOKEN}"
  ```

  **注意**，登陆是出现 `Not enough data to create auth info structure` 时，重新执行本命令即可！

  ![](.\images\kube-config-dir.png)

* 登录 dashboard 的时候选择 `C:\Users\XXX\.kube\config` 文件

  ![](.\images\k8s_credentials.png)

* 点击 `登录` 成功

  ![](.\images\kubernetes-dashboard.png)

## 安装并运行容器（Redis）

* 本案例采用 Redis 演示

### 查找镜像

```bash
docker search redis[:5.5]
```

![](.\images\docker-search-redis.png)

### 拉取镜像

```bash
docker pull redis[:5.5]
```

![](.\images\docker-pull-redis.png)

### 查看本地镜像

```bash
docker images
```

![](.\images\docker-images.png)

### 创建并运行容器

```bash
docker run -d --restart=always --name redis -p 6379:6379 -v /D/DockerData/redis/data:/data redis:latest --appendonly yes --requirepass "123456"
```

![](.\images\docker-run-redis.png)

### 列出容器

```bash
docker ps -as
```

![](.\images\docker-ps-as.png)

### 客户端接连 redis（检查可用）

![](.\images\connect-docker-redis-test.png)

## 用到相关 Docker 命令

### 命令大集

* https://www.runoob.com/docker/docker-command-manual.html
* https://www.jianshu.com/p/afb20541d781
* https://www.w3xue.com/manual/docker/

### 查找镜像

```bash
docker search redis[:5.5]
```

### 拉取镜像

```bash
docker pull redis[:5.5]
```

### 查看本地镜像

```bash
docker images
```

### 删除本地镜像

```bash
docker rmi <IMAGE ID>
```

### 创建并运行容器

```bash
docker run -help
```

### 停止运行镜像

* NAMES 可能过 docker ps -as 命令获得

```bash
docker stop <NAMES>
```

### 删除运行镜像

* NAMES可能过 docker ps -as 命令获得

```bash
docker rm <NAMES>
```

### 获取容器的日志

* NAMES可能过 docker ps -as 命令获得

```bash
docker logs <CONTAINER ID>
```

### 在运行的容器中执行命令

```bash
docker exec -it <CONTAINER ID> bash
```

### 列出容器

```bash
docker ps -as
```

### 复制文件（主机->容器）

* 从主机复制到容器 docker cp [OPTIONS] SRC_PATH|- CONTAINER:DEST_PATH

```bash
docker cp /host/path/target 容器ID:/file/path/within/container
```

### 复制文件（容器->主机）

* 从容器复制到主机 docker cp [OPTIONS] CONTAINER:SRC_PATH DEST_PATH|-

```bash
docker cp 容器ID:/file/path/within/container /host/path/target
```

## 其他容器创建运行

### zookeeper

```bash
docker run -d --restart=always --name zookeeper -p 2181:2181 -v /D/DockerData/zookeeper/zk-1/data:/data -v /D/DockerData/zookeeper/zk-1/datalog:/datalog zookeeper:latest
```

### nacos

```bash
docker run -d --restart=always --name nacos --env MODE=standalone -p 8848:8848 nacos/nacos-server:latest
```

### mysql

```bash
docker run -d --restart=always --name mysql -p 3306:3306 -v /D/DockerData/mysql/mysql:/var/lib/mysql -v /D/DockerData/mysql/conf:/etc/mysql/conf.d -e MYSQL_ROOT_PASSWORD=duanbo mysql:latest --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
```

### jenkins

```bash
docker run -d --restart=always --name jenkins -p 8088:8080 -p 50000:50000 --restart always -v /D/DockerData/jenkins/jenkins_home:/var/jenkins_home -t jenkins:latest
```

### zookeeper 集群（未实验）

```bash
-- 机器ip
$machine_ip=10.0.75.1

-- 运行节点1
docker run -d --restart always --name zk-1 -p 2181:2181 -p 2887:2888 -p 3887:3888 \
-v /D/DockerData/zookeeper/zk-1/data:/data \
-v /D/DockerData/zookeeper/zk-1/datalog:/datalog \
-e "TZ=Asia/Shanghai" \
-e "ZOO_MY_ID=1" \
-e "ZOO_SERVERS=server.1=0.0.0.0:2888:3888 server.2=${machine_ip}:2888:3888 server.3=${machine_ip}:2889:3889" \
zookeeper:latest

-- 运行节点2
docker run -d --restart always --name zk-2 -p 2182:2181 -p 2888:2888 -p 3888:3888 \
-v /D/DockerData/zookeeper/zk-2/data:/data \
-v /D/DockerData/zookeeper/zk-2/datalog:/datalog \
-e "TZ=Asia/Shanghai" \
-e "ZOO_MY_ID=2" \
-e "ZOO_SERVERS=server.1=${machine_ip}:2887:3887 server.2=0.0.0.0:2888:3888 server.3=${machine_ip}:2889:3889" \
zookeeper:latest

-- 运行节点3
docker run -d --restart always --name zk-3 -p 2183:2181 -p 2889:2888 -p 3889:3888 \
-v /D/DockerData/zookeeper/zk-3/data:/data \
-v /D/DockerData/zookeeper/zk-3/datalog:/datalog \
-e "TZ=Asia/Shanghai" \
-e "ZOO_MY_ID=3" \
-e "ZOO_SERVERS=server.1=${machine_ip}:2887:3887 server.2=${machine_ip}:2888:3888 server.3=0.0.0.0:2888:3888" \
zookeeper:latest
```

