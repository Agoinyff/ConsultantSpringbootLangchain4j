/**
 * 滚动控制 Composable
 */

import { ref, nextTick } from 'vue'

/**
 * 滚动控制组合式函数
 * @returns {Object} 滚动相关状态和方法
 */
export function useScroll() {
    const containerRef = ref(null)
    const isAutoScrollEnabled = ref(true)

    /**
     * 滚动到底部
     * @param {Object} options - 滚动选项
     * @param {boolean} options.smooth - 是否平滑滚动
     */
    const scrollToBottom = async ({ smooth = true } = {}) => {
        await nextTick()

        if (containerRef.value && isAutoScrollEnabled.value) {
            containerRef.value.scrollTo({
                top: containerRef.value.scrollHeight,
                behavior: smooth ? 'smooth' : 'auto'
            })
        }
    }

    /**
     * 滚动到顶部
     * @param {Object} options - 滚动选项
     */
    const scrollToTop = async ({ smooth = true } = {}) => {
        await nextTick()

        if (containerRef.value) {
            containerRef.value.scrollTo({
                top: 0,
                behavior: smooth ? 'smooth' : 'auto'
            })
        }
    }

    /**
     * 检查是否滚动到底部
     * @returns {boolean}
     */
    const isAtBottom = () => {
        if (!containerRef.value) return true

        const { scrollTop, scrollHeight, clientHeight } = containerRef.value
        return scrollHeight - scrollTop - clientHeight < 50
    }

    /**
     * 处理滚动事件，判断是否启用自动滚动
     */
    const handleScroll = () => {
        isAutoScrollEnabled.value = isAtBottom()
    }

    return {
        containerRef,
        isAutoScrollEnabled,
        scrollToBottom,
        scrollToTop,
        isAtBottom,
        handleScroll
    }
}
