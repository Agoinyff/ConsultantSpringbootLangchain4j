/**
 * HTTP 请求封装
 */

import { BASE_URL, TIMEOUT, HTTP_STATUS } from '@/config'

/**
 * 基础请求方法
 * @param {string} url - 请求 URL
 * @param {Object} options - 请求选项
 * @returns {Promise<Response>}
 */
export const request = async (url, options = {}) => {
    const fullUrl = `${BASE_URL}${url}`

    const controller = new AbortController()
    const timeoutId = setTimeout(() => controller.abort(), options.timeout || TIMEOUT)

    try {
        const response = await fetch(fullUrl, {
            ...options,
            signal: controller.signal
        })

        clearTimeout(timeoutId)

        if (!response.ok) {
            throw new Error(`HTTP Error: ${response.status}`)
        }

        return response
    } catch (error) {
        clearTimeout(timeoutId)

        if (error.name === 'AbortError') {
            throw new Error('请求超时，请重试')
        }

        throw error
    }
}

/**
 * GET 请求
 * @param {string} url - 请求 URL
 * @param {Object} params - 查询参数
 * @param {Object} options - 请求选项
 * @returns {Promise<Response>}
 */
export const get = (url, params = {}, options = {}) => {
    const queryString = new URLSearchParams(params).toString()
    const fullUrl = queryString ? `${url}?${queryString}` : url

    return request(fullUrl, {
        method: 'GET',
        ...options
    })
}

/**
 * POST 请求
 * @param {string} url - 请求 URL
 * @param {Object} data - 请求体数据
 * @param {Object} options - 请求选项
 * @returns {Promise<Response>}
 */
export const post = (url, data = {}, options = {}) => {
    return request(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            ...options.headers
        },
        body: JSON.stringify(data),
        ...options
    })
}
