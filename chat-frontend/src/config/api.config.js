/**
 * API 配置文件
 */

// 基础 URL 配置
export const BASE_URL = '/api'

// 请求超时时间
export const TIMEOUT = 30000

// API 端点配置
export const API_ENDPOINTS = {
    CHAT: '/chat'
}

// HTTP 状态码
export const HTTP_STATUS = {
    OK: 200,
    BAD_REQUEST: 400,
    UNAUTHORIZED: 401,
    FORBIDDEN: 403,
    NOT_FOUND: 404,
    INTERNAL_ERROR: 500
}
