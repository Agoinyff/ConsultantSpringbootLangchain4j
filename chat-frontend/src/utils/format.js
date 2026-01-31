/**
 * 文本格式化工具函数
 */

/**
 * HTML 转义，防止 XSS 攻击
 * @param {string} text - 原始文本
 * @returns {string} - 转义后的文本
 */
export const escapeHtml = (text) => {
    const map = {
        '&': '&amp;',
        '<': '&lt;',
        '>': '&gt;',
        '"': '&quot;',
        "'": '&#039;'
    }
    return text.replace(/[&<>"']/g, (char) => map[char])
}

/**
 * 格式化消息内容（转义 HTML 并转换换行）
 * @param {string} content - 消息内容
 * @returns {string} - 格式化后的 HTML
 */
export const formatMessage = (content) => {
    if (!content) return ''
    return escapeHtml(content).replace(/\n/g, '<br>')
}

/**
 * 截断文本
 * @param {string} text - 原始文本
 * @param {number} maxLength - 最大长度
 * @param {string} suffix - 后缀
 * @returns {string}
 */
export const truncateText = (text, maxLength = 100, suffix = '...') => {
    if (!text || text.length <= maxLength) return text
    return text.slice(0, maxLength) + suffix
}

/**
 * 生成唯一 ID
 * @returns {string}
 */
export const generateId = () => {
    return `${Date.now()}-${Math.random().toString(36).slice(2, 11)}`
}
