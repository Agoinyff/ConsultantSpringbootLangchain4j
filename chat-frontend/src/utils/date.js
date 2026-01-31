/**
 * 日期时间工具函数
 */

/**
 * 获取当前时间格式化字符串
 * @param {string} format - 格式类型 'time' | 'date' | 'datetime'
 * @returns {string}
 */
export const getCurrentTime = (format = 'time') => {
    const now = new Date()

    const options = {
        time: { hour: '2-digit', minute: '2-digit' },
        date: { year: 'numeric', month: '2-digit', day: '2-digit' },
        datetime: {
            year: 'numeric',
            month: '2-digit',
            day: '2-digit',
            hour: '2-digit',
            minute: '2-digit'
        }
    }

    return now.toLocaleString('zh-CN', options[format] || options.time)
}

/**
 * 格式化时间戳
 * @param {number} timestamp - 时间戳
 * @param {string} format - 格式类型
 * @returns {string}
 */
export const formatTimestamp = (timestamp, format = 'time') => {
    const date = new Date(timestamp)
    return getCurrentTime.call({ now: date }, format)
}
