[TOC]

# gitlab/gitlab-ce 安装

## 查找镜像版本

* 通过官网URL查找所需要的版本

```html
https://hub.docker.com/r/gitlab/gitlab-ce/tags
```

* 这里选择 `gitlab/gitlab-ce:12.5.4-ce.0` 版本

##  创建并运行容器

```bash
# 宿主机创建数据目录
mkdir -p /boazy/data/dockerdata/gitlab
# 拉取镜像（可跳过）
docker pull gitlab/gitlab-ce:12.5.4-ce.0

# 创建并运行镜像容器
docker run -d \
    --hostname gitlab.boazy.com \
    -P -p 443:443 -p 80:80 -p 2280:22 \
    --name gitlab \
    --restart always \
    --privileged=true \
    -v /boazy/data/dockerdata/gitlab/etc:/etc/gitlab \
    -v /boazy/data/dockerdata/gitlab/log:/var/log/gitlab \
    -v /boazy/data/dockerdata/gitlab/data:/var/opt/gitlab \
    -v /etc/localtime:/etc/localtime:ro \
    gitlab/gitlab-ce:12.5.4-ce.0
```


