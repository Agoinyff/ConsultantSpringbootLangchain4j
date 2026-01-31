/**
 * 输入控制 Composable
 */

import { ref, nextTick } from 'vue'

/**
 * 输入控制组合式函数
 * @returns {Object} 输入相关状态和方法
 */
export function useInput() {
    const inputRef = ref(null)
    const inputValue = ref('')

    /**
     * 聚焦输入框
     */
    const focusInput = async () => {
        await nextTick()
        inputRef.value?.focus()
    }

    /**
     * 清空输入框
     */
    const clearInput = () => {
        inputValue.value = ''
        adjustTextareaHeight()
    }

    /**
     * 自动调整 textarea 高度
     */
    const adjustTextareaHeight = () => {
        const textarea = inputRef.value
        if (textarea) {
            textarea.style.height = 'auto'
            textarea.style.height = Math.min(textarea.scrollHeight, 120) + 'px'
        }
    }

    /**
     * 处理 Enter 键
     * @param {KeyboardEvent} event - 键盘事件
     * @param {Function} onSubmit - 提交回调
     */
    const handleEnter = (event, onSubmit) => {
        if (!event.shiftKey) {
            event.preventDefault()
            onSubmit?.()
        }
    }

    /**
     * 设置输入值
     * @param {string} value - 输入值
     */
    const setInputValue = (value) => {
        inputValue.value = value
        nextTick(adjustTextareaHeight)
    }

    return {
        inputRef,
        inputValue,
        focusInput,
        clearInput,
        adjustTextareaHeight,
        handleEnter,
        setInputValue
    }
}
