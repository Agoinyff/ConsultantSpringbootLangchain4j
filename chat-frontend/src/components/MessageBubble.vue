<template>
  <div class="message fade-in-up" :class="role">
    <div class="message-avatar">
      <div v-if="role === 'user'" class="user-avatar">
        <svg viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
          <path d="M20 21V19C20 17.9391 19.5786 16.9217 18.8284 16.1716C18.0783 15.4214 17.0609 15 16 15H8C6.93913 15 5.92172 15.4214 5.17157 16.1716C4.42143 16.9217 4 17.9391 4 19V21" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
          <circle cx="12" cy="7" r="4" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
        </svg>
      </div>
      <div v-else class="ai-avatar">
        <svg viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
          <path d="M12 2L2 7L12 12L22 7L12 2Z" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
          <path d="M2 17L12 22L22 17" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
          <path d="M2 12L12 17L22 12" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
        </svg>
      </div>
    </div>
    <div class="message-content">
      <div class="message-bubble">
        <span v-html="formattedContent"></span>
        <span v-if="isStreaming" class="cursor">|</span>
      </div>
      <div class="message-time">{{ time }}</div>
    </div>
  </div>
</template>

<script setup>
import { computed } from 'vue'
import { formatMessage } from '@/utils'

const props = defineProps({
  role: {
    type: String,
    required: true,
    validator: (value) => ['user', 'assistant'].includes(value)
  },
  content: {
    type: String,
    default: ''
  },
  time: {
    type: String,
    default: ''
  },
  isStreaming: {
    type: Boolean,
    default: false
  }
})

const formattedContent = computed(() => formatMessage(props.content))
</script>

<style scoped>
.message {
  display: flex;
  gap: 12px;
  margin-bottom: 20px;
}

.message.user {
  flex-direction: row-reverse;
}

.message-avatar {
  flex-shrink: 0;
}

.user-avatar,
.ai-avatar {
  width: 36px;
  height: 36px;
  border-radius: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.user-avatar {
  background: var(--user-bubble);
}

.ai-avatar {
  background: var(--bg-card);
  border: 1px solid var(--border-color);
}

.user-avatar svg,
.ai-avatar svg {
  width: 18px;
  height: 18px;
  color: white;
}

.ai-avatar svg {
  color: var(--text-secondary);
}

.message-content {
  max-width: 70%;
}

.message-bubble {
  padding: 12px 16px;
  border-radius: 16px;
  line-height: 1.6;
  font-size: 0.9375rem;
}

.message.user .message-bubble {
  background: var(--user-bubble);
  border-bottom-right-radius: 4px;
}

.message.assistant .message-bubble {
  background: var(--ai-bubble);
  border: 1px solid var(--border-color);
  border-bottom-left-radius: 4px;
}

.message-time {
  font-size: 0.75rem;
  color: var(--text-muted);
  margin-top: 6px;
  padding: 0 4px;
}

.message.user .message-time {
  text-align: right;
}

.cursor {
  animation: pulse 1s infinite;
  color: #667eea;
  font-weight: bold;
}

@keyframes pulse {
  0%, 100% {
    opacity: 1;
  }
  50% {
    opacity: 0.5;
  }
}

@media (max-width: 640px) {
  .message-content {
    max-width: 85%;
  }
}
</style>
