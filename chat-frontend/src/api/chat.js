/**
 * 聊天相关 API
 */

import { BASE_URL, API_ENDPOINTS } from '@/config'

/**
 * 发送聊天消息（流式响应）
 * @param {string} message - 用户消息
 * @param {string} memoryId - 会话记忆 ID
 * @param {Object} callbacks - 回调函数对象
 * @param {Function} callbacks.onMessage - 收到消息片段时的回调
 * @param {Function} callbacks.onError - 发生错误时的回调
 * @param {Function} callbacks.onComplete - 完成时的回调
 * @returns {Promise<void>}
 */
export const sendChatMessage = async (message, memoryId, { onMessage, onError, onComplete }) => {
    const url = `${BASE_URL}${API_ENDPOINTS.CHAT}?memoryId=${encodeURIComponent(memoryId)}&message=${encodeURIComponent(message)}`

    try {
        const response = await fetch(url)

        if (!response.ok) {
            throw new Error(`HTTP Error: ${response.status}`)
        }

        const reader = response.body.getReader()
        const decoder = new TextDecoder('utf-8')

        // 持续读取流数据
        while (true) {
            const { done, value } = await reader.read()

            if (done) {
                onComplete?.()
                break
            }

            // 解码当前数据块
            const chunk = decoder.decode(value, { stream: true })

            // 直接输出接收到的内容（Flux<String> 返回的是纯文本）
            if (chunk) {
                onMessage?.(chunk)
            }
        }
    } catch (error) {
        console.error('Chat API Error:', error)
        onError?.(error)
    }
}

/**
 * 创建 AbortController 用于取消请求
 * @returns {AbortController}
 */
export const createChatAbortController = () => {
    return new AbortController()
}
