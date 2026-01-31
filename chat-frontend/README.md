# AI 智能咨询助手 - 前端

基于 Vue 3 + Vite 构建的现代化聊天前端应用。

## 项目结构

```
chat-frontend/
├── src/
│   ├── api/                # API 层 - 接口请求封装
│   │   ├── index.js        # 统一导出
│   │   ├── request.js      # 基础请求封装
│   │   └── chat.js         # 聊天相关 API
│   │
│   ├── components/         # 组件层 - UI 组件
│   │   ├── index.js        # 统一导出
│   │   ├── ChatHeader.vue  # 头部组件
│   │   ├── MessageBubble.vue   # 消息气泡
│   │   ├── MessageInput.vue    # 输入框
│   │   ├── TypingIndicator.vue # 打字指示器
│   │   └── WelcomeScreen.vue   # 欢迎屏幕
│   │
│   ├── composables/        # 组合式函数层 - 可复用逻辑
│   │   ├── index.js        # 统一导出
│   │   ├── useChat.js      # 聊天状态管理
│   │   ├── useScroll.js    # 滚动控制
│   │   └── useInput.js     # 输入控制
│   │
│   ├── config/             # 配置层 - 常量配置
│   │   ├── index.js        # 统一导出
│   │   ├── api.config.js   # API 配置
│   │   └── app.config.js   # 应用配置
│   │
│   ├── utils/              # 工具层 - 通用工具函数
│   │   ├── index.js        # 统一导出
│   │   ├── date.js         # 日期工具
│   │   └── format.js       # 格式化工具
│   │
│   ├── views/              # 视图层 - 页面组件
│   │   ├── index.js        # 统一导出
│   │   └── ChatView.vue    # 聊天页面
│   │
│   ├── App.vue             # 根组件
│   ├── main.js             # 入口文件
│   └── style.css           # 全局样式
│
├── index.html              # HTML 模板
├── vite.config.js          # Vite 配置
├── jsconfig.json           # JS 配置
├── package.json            # 项目依赖
└── README.md               # 项目文档
```

## 分层说明

| 层级 | 职责 | 说明 |
|------|------|------|
| **config** | 配置管理 | 集中管理 API 地址、常量等配置 |
| **utils** | 工具函数 | 通用的、无状态的工具函数 |
| **api** | 接口请求 | 封装 HTTP 请求和业务 API |
| **composables** | 组合逻辑 | 可复用的有状态逻辑 |
| **components** | UI 组件 | 可复用的 UI 组件 |
| **views** | 页面视图 | 页面级组件，组合多个组件 |

## 快速开始

```bash
# 安装依赖
npm install

# 启动开发服务器
npm run dev

# 构建生产版本
npm run build

# 预览生产版本
npm run preview
```

## 开发规范

### 命名规范

- **文件夹**: 小写，多单词用连字符（kebab-case）
- **组件文件**: 大驼峰（PascalCase），如 `MessageBubble.vue`
- **JS 文件**: 小驼峰（camelCase），如 `useChat.js`
- **CSS 类名**: 小写连字符（kebab-case），如 `.message-bubble`

### 导入规范

使用 `@` 别名进行导入：

```javascript
// 推荐
import { useChat } from '@/composables'
import { formatMessage } from '@/utils'
import { API_ENDPOINTS } from '@/config'

// 不推荐
import { useChat } from '../../composables'
```

## 技术栈

- **Vue 3** - 渐进式 JavaScript 框架
- **Vite** - 下一代前端构建工具
- **Composition API** - Vue 3 组合式 API
