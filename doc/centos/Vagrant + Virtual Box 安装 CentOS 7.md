# Vagrant + Virtual Box 安装 CentOS 7

## 安装 Vagrant

### 下载

* https://www.vagrantup.com

* 选择对应的版本 Windows/MacOS/Linux 下载

### 安装

* 下载后安装
* 安装后采用命令 vagrant 测试是否安装成功



## 安装 Virtual Box

### 下载

* https://www.virtualbox.org
* 选择对应的版本 Windows/MacOS/Linux 下载

### 安装

* 下载后安装



## 安装 CentOS 7

### 准备 CentOS 7 的 box 文件

* https://vagrantcloud.com/centos/boxes/7/versions/1905.1/providers/virtualbox.box

### 添加下载的 CentOS 7 的 box 到 vagrant

* 添加之前用命令 vagrant box list 查看 vagrant 本地 box 时是没有的

  ```bash
  vagrant box list
  ```

* vagrant box add centos/7 D:\downloads\virtualbox.box

  ```bash
  vagrant box add centos/7 D:\downloads\virtualbox.box
  ```

* 添加之后用命令 vagrant box list 查看 vagrant 本地 box 时是可以看到 centos/7 了

  ```bash
  vagrant box list
  ```

### 生成 Vagrantfile 文件

* 创建定指定目录，并在当前目录运行命令生成

  ```bash
  vagrant init centos/7
  ```

* 创建成功后可修改 Vagrantfile 文件信息

  ```bash
    # boxes at https://vagrantcloud.com/search.
    config.vm.box = "centos/7"
  
    # Disable automatic box update checking. If you disable this, then
    ...
    # config.vm.network "public_network"
    config.vm.network "public_network"
  
    # Share an additional folder to the guest VM. The first argument is
    ...
    #   vb.memory = "1024"
    # end
    
    config.vm.provider "virtualbox" do |vb|
          vb.memory = "4096"
          vb.name= "boazy-centos7"
          vb.cpus= 2
      end
    
    #
    # View the documentation for the provider you are using for more
    # information on available options.
    ...
  ```

### 创建 CentOS 7 虚拟机

* 创建命令

  ```bash
  vagrant up
  ```

* 打开 Virtual Box 看可以看到 CentOS 7 已创建成功

### 配置 CentOS 7 虚拟机

#### 默认 SSH 登陆用户名/密码

```bash
vagrant/vagrant
```

#### root 用户名/密码

* root 用户登陆需要开启

  ```bash
  sudo -i
  vi /etc/ssh/sshd_config
  ```

  ```bash
  # 修改文件内容属性值为 yes
  PasswordAuthentication yes
  ```

  ```bash
  systemctl restart sshd
  ```

* root 用户名/密码

  ```bash
  root/vagrant
  ```

  