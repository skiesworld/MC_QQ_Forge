# MC_QQ_Forge

> [!WARNING]  
> 项目已归档，请前往 [鹊桥](https://github.com/17TheWord/QueQiao) 获得全新体验

> [!WARNING]  
> 项目已归档，请前往 [鹊桥](https://github.com/17TheWord/QueQiao) 获得全新体验

> [!WARNING]  
> 项目已归档，请前往 [鹊桥](https://github.com/17TheWord/QueQiao) 获得全新体验

一个简单的 `Forge MOD`，可以通过 [`nonebot-adapter-minecraft`](https://github.com/17TheWord/nonebot-adapter-minecraft)
连接至 [`nonebot2`](https://github.com/nonebot/nonebot2)。

## 使用

前往适配器的 [Wiki](https://github.com/17TheWord/nonebot-adapter-minecraft/wiki)

## 自行构建

1. 克隆项目

    ```shell
    git clone https://github.com/17TheWord/MC_QQ_Forge.git
    cd MC_QQ_Forge
    ```

2. 配置工具包仓库的个人访问令牌
   - `mc-qq-tool` 依赖位于 https://github.com/17TheWord/MC_QQ_Tool
   - 需要配置只读的 `Package Token`
   - 参考 [GitHub Packages 文档](https://docs.github.com/zh/packages/working-with-a-github-packages-registry/working-with-the-gradle-registry#%E5%90%91-github-packages-%E9%AA%8C%E8%AF%81)
   - 该项目使用的环境变量名
       - 必填
           - `USERNAME`：GitHub 用户名
           - `PACKAGE_READ_ONLY_TOKEN`：GitHub 个人访问令牌
       - 可选
           - `MC_QQ_VERSION`：项目版本号（或在 `gradle.properties` 中通过 `mod_version=x.x.x` 配置版本号）
           - `LOMBOK_VERSION`：Lombok 版本号（或在 `gradle.properties` 中通过 `lombok_version=x.x.x` 配置版本号）
           - `MCQQ_TOOL_VERSION`：工具包版本号（或在 `gradle.properties` 中通过 `mcqq_tool_version=x.x.x` 配置版本号）

3. 构建项目

    ```shell
    ./gradlew build
    ```

## 贡献与支持

觉得好用可以给这个项目点个 `Star` 或者去 [爱发电](https://afdian.net/a/17TheWord) 投喂我。

有意见或者建议也欢迎提交 [Issues](https://github.com/17TheWord/MC_QQ_Forge/issues)
和 [Pull requests](https://github.com/17TheWord/MC_QQ_Forge/pulls) 。

## 开源许可

本项目使用 [MIT](./LICENSE) 作为开源许可证。
