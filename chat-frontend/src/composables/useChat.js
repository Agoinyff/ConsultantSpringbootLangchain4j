/**
 * 聊天状态管理 Composable
 */

import { ref, computed } from 'vue'
import { sendChatMessage } from '@/api'
import { getCurrentTime, generateId, formatMessage } from '@/utils'
import { APP_CONFIG } from '@/config'

/**
 * 聊天功能组合式函数
 * @returns {Object} 聊天相关状态和方法
 */
export function useChat() {
    // 状态
    const messages = ref([])
    const isLoading = ref(false)
    const isTyping = ref(false)
    const isConnected = ref(true)
    const currentStreamingMessage = ref(null)

    // 会话记忆 ID（使用时间戳，整个会话期间保持不变）
    const memoryId = ref(Date.now().toString())

    // 计算属性
    const hasMessages = computed(() => messages.value.length > 0)
    const lastMessage = computed(() => messages.value[messages.value.length - 1])

    /**
     * 添加用户消息
     * @param {string} content - 消息内容
     * @returns {Object} 消息对象
     */
    const addUserMessage = (content) => {
        const message = {
            id: generateId(),
            role: 'user',
            content,
            time: getCurrentTime()
        }
        messages.value.push(message)
        return message
    }

    /**
     * 添加 AI 消息
     * @param {string} content - 消息内容
     * @param {boolean} isStreaming - 是否正在流式输出
     * @returns {Object} 消息对象
     */
    const addAssistantMessage = (content = '', isStreaming = false) => {
        const message = {
            id: generateId(),
            role: 'assistant',
            content,
            time: getCurrentTime(),
            isStreaming
        }
        messages.value.push(message)
        return message
    }

    /**
     * 发送消息
     * @param {string} content - 消息内容
     */
    const sendMessage = async (content) => {
        const trimmedContent = content.trim()
        if (!trimmedContent || isLoading.value) return

        // 验证消息长度
        if (trimmedContent.length > APP_CONFIG.MESSAGE.MAX_LENGTH) {
            console.warn('Message too long')
            return
        }

        // 添加用户消息
        addUserMessage(trimmedContent)

        // 设置加载状态
        isLoading.value = true
        isTyping.value = true

        // 创建 AI 消息占位符
        const assistantMessage = addAssistantMessage('', true)
        currentStreamingMessage.value = assistantMessage

        try {
            await sendChatMessage(trimmedContent, memoryId.value, {
                onMessage: (data) => {
                    assistantMessage.content += data
                },
                onError: (error) => {
                    console.error('Chat error:', error)
                    isConnected.value = false
                    assistantMessage.content = '抱歉，连接出现问题，请稍后重试。'
                    assistantMessage.isStreaming = false
                },
                onComplete: () => {
                    assistantMessage.isStreaming = false
                    isConnected.value = true
                }
            })
        } catch (error) {
            console.error('Send message error:', error)
            assistantMessage.content = '抱歉，发送消息失败，请重试。'
            assistantMessage.isStreaming = false
            isConnected.value = false
        } finally {
            isLoading.value = false
            isTyping.value = false
            currentStreamingMessage.value = null
        }
    }

    /**
     * 清空消息并重置会话
     */
    const clearMessages = () => {
        messages.value = []
        // 重置会话 ID，开启新会话
        memoryId.value = Date.now().toString()
    }

    /**
     * 获取格式化后的消息内容
     * @param {string} content - 原始内容
     * @returns {string} 格式化后的 HTML
     */
    const getFormattedMessage = (content) => {
        return formatMessage(content)
    }

    return {
        // 状态
        messages,
        isLoading,
        isTyping,
        isConnected,
        currentStreamingMessage,

        // 计算属性
        hasMessages,
        lastMessage,

        // 方法
        sendMessage,
        clearMessages,
        getFormattedMessage
    }
}
