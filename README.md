# 项目简介

该项目是一个类似于抖音Web端的项目，实现了一个Web端的短视频应用基础基功能。

项目包含功能如下

- **视频播放**: 播放、暂停、进度条拖拽
- **内容分类**: 视频内容分类页，如热门视频、体育频道
- **视频切换**: 可通过上下键翻看视频

为了增强用户体验，还实现了**账户系统、个人中心、上传、收藏、点赞、分享、关注、搜索、评论**等功能

# 项目技术栈

- **前端技术：**使用Vue进行项目的搭建，利用Element-Plus进行界面美化以及快速开发
- **后端技术：**使用SpringBoot、SpringCloud Alibaba完成项目的搭建，提供相应的接口
- **数据存储**：使用Mysql进行数据的存储，Redis用于数据的缓存
- **文件存储**：使用七牛云存储服务完成文件的读写操作

# 视频介绍与架构设计文档

* **架构设计文档地址为**：

* **视频介绍地址为**：

# 安装说明

项目是前后端分离的，需要分别clone到本地，然后运行

## 前端安装说明

前端地址为：https://github.com/L520C/qny-vue

前端项目建议使用Node版本为 **v20.8.1**。项目安装运行步骤如下

### 1、代码克隆到本地

```
git clone https://github.com/L520C/qny-vue.git
```

### 2、依赖安装

```
npm install
```

### 3、项目运行

```
npm run serve
```

默认的访问地址为 http://localhost:8081/

## 后端安装说明

后端地址为：https://github.com/c-ttpfx/qny

**Jdk版本应当使用8**，Maven版本建议使用3.8.6。项目安装运行步骤如下

### 1、代码克隆到本地

```
git clone https://github.com/c-ttpfx/qny.git
```

###  2、使用IDEA打开项目

![image-20231107183137930](https://raw.githubusercontent.com/c-ttpfx/markdown-image/main/typora-img/image-20231107183137930.png)

项目包含4个模块，功能分别如下

* **common模块**：存放公共代码，所有模块均能够使用
* **gateway模块**：进行网关的配置，请求的拦截和转发
* **user模块**：与用户相关的功能在这个模块进行处理
* **video模块**：视频相关功能在这个模块进行处理

### 3、修改配置文件

分别修改每个模块里面的application.yaml文件，**将Mysql，Redis，Nacos，启动端口设置为自己的**

### 4、执行qny.sql文件

打开Navicat，执行qny.sql文件，该sql文件存放于doc目录下

### 5、启动Nacos

建议使用的Nacos版本为2.2.3，该版本的Nacos会存放于doc目录下

### 6、分别启动上述模块

首先启动gateway模块，然后启动user模块，最后启动video模块

## 注意事项

为了确保项目的成功启动，建议少修改项目里面的内容。都将前端和后端运行在本地即可。**游览器请选择Chrome**进行访问

# 项目功能截图

>  登录界面

![image-20231107185440791](https://raw.githubusercontent.com/c-ttpfx/markdown-image/main/typora-img/image-20231107185440791.png)

> 视频推荐界面

![image-20231107185513492](https://raw.githubusercontent.com/c-ttpfx/markdown-image/main/typora-img/image-20231107185513492.png)

> 评论区

![image-20231107185705557](https://raw.githubusercontent.com/c-ttpfx/markdown-image/main/typora-img/image-20231107185705557.png)

> 分享界面

![image-20231107185752183](https://raw.githubusercontent.com/c-ttpfx/markdown-image/main/typora-img/image-20231107185752183.png)

> 分享查看界面

![image-20231107185843692](https://raw.githubusercontent.com/c-ttpfx/markdown-image/main/typora-img/image-20231107185843692.png)

> 分享小窗查看

![image-20231107190301228](https://raw.githubusercontent.com/c-ttpfx/markdown-image/main/typora-img/image-20231107190301228.png)

> 个人中心

![image-20231107185936928](https://raw.githubusercontent.com/c-ttpfx/markdown-image/main/typora-img/image-20231107185936928.png)

> 热门视频



> 其它标签页



> 视频预览功能

![image-20231107190111323](https://raw.githubusercontent.com/c-ttpfx/markdown-image/main/typora-img/image-20231107190111323.png)

> 搜索功能



> 作品上传

![image-20231107190155990](https://raw.githubusercontent.com/c-ttpfx/markdown-image/main/typora-img/image-20231107190155990.png)

