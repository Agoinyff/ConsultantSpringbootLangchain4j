<template>
  <header class="chat-header">
    <div class="header-content">
      <div class="logo">
        <div class="logo-icon">
          <slot name="icon">
            <svg viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
              <path d="M12 2L2 7L12 12L22 7L12 2Z" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
              <path d="M2 17L12 22L22 17" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
              <path d="M2 12L12 17L22 12" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
            </svg>
          </slot>
        </div>
        <div class="logo-text">
          <h1>{{ title }}</h1>
          <span class="status" :class="{ online: isOnline }">
            <span class="status-dot"></span>
            {{ isOnline ? '在线' : '离线' }}
          </span>
        </div>
      </div>
      <div class="header-actions">
        <slot name="actions">
          <button class="action-btn" @click="$emit('clear')" title="清空对话">
            <svg viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
              <path d="M3 6H5H21" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
              <path d="M8 6V4C8 3.44772 8.44772 3 9 3H15C15.5523 3 16 3.44772 16 4V6M19 6V20C19 20.5523 18.5523 21 18 21H6C5.44772 21 5 20.5523 5 20V6H19Z" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
            </svg>
          </button>
        </slot>
      </div>
    </div>
  </header>
</template>

<script setup>
import { APP_CONFIG } from '@/config'

defineProps({
  title: {
    type: String,
    default: APP_CONFIG.APP_NAME
  },
  isOnline: {
    type: Boolean,
    default: true
  }
})

defineEmits(['clear'])
</script>

<style scoped>
.chat-header {
  padding: 16px 0;
  border-bottom: 1px solid var(--border-color);
  flex-shrink: 0;
}

.header-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.logo {
  display: flex;
  align-items: center;
  gap: 12px;
}

.logo-icon {
  width: 44px;
  height: 44px;
  background: var(--primary-gradient);
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow: var(--shadow-glow);
}

.logo-icon svg {
  width: 24px;
  height: 24px;
  color: white;
}

.logo-text h1 {
  font-size: 1.25rem;
  font-weight: 600;
  background: var(--primary-gradient);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

.status {
  display: flex;
  align-items: center;
  gap: 6px;
  font-size: 0.75rem;
  color: var(--text-muted);
}

.status-dot {
  width: 8px;
  height: 8px;
  border-radius: 50%;
  background: #6b7280;
}

.status.online .status-dot {
  background: #10b981;
  box-shadow: 0 0 8px rgba(16, 185, 129, 0.5);
}

.header-actions {
  display: flex;
  gap: 8px;
}

.action-btn {
  width: 40px;
  height: 40px;
  border: none;
  background: var(--bg-card);
  border-radius: 10px;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: all 0.2s ease;
}

.action-btn:hover {
  background: var(--bg-card-hover);
  transform: scale(1.05);
}

.action-btn svg {
  width: 20px;
  height: 20px;
  color: var(--text-secondary);
}
</style>
