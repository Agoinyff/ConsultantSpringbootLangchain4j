<template>
  <div class="welcome-screen">
    <div class="welcome-icon">
      <svg viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
        <path d="M21 11.5C21.0034 12.8199 20.6951 14.1219 20.1 15.3C19.3944 16.7118 18.3098 17.8992 16.9674 18.7293C15.6251 19.5594 14.0782 19.9994 12.5 20C11.1801 20.0035 9.87812 19.6951 8.7 19.1L3 21L4.9 15.3C4.30493 14.1219 3.99656 12.8199 4 11.5C4.00061 9.92179 4.44061 8.37488 5.27072 7.03258C6.10083 5.69028 7.28825 4.6056 8.7 3.90003C9.87812 3.30496 11.1801 2.99659 12.5 3.00003H13C15.0843 3.11502 17.053 3.99479 18.5291 5.47089C20.0052 6.94699 20.885 8.91568 21 11V11.5Z" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
      </svg>
    </div>
    <h2>{{ title }}</h2>
    <p>{{ description }}</p>
    <div class="suggestion-chips">
      <button 
        v-for="suggestion in suggestions" 
        :key="suggestion"
        class="chip"
        @click="$emit('select-suggestion', suggestion)"
      >
        {{ suggestion }}
      </button>
    </div>
  </div>
</template>

<script setup>
import { APP_CONFIG, SUGGESTION_QUESTIONS } from '@/config'

defineProps({
  title: {
    type: String,
    default: `欢迎使用 ${APP_CONFIG.APP_NAME}`
  },
  description: {
    type: String,
    default: '我是您的智能助手，可以帮助您解答各种问题。请在下方输入您的问题开始对话。'
  },
  suggestions: {
    type: Array,
    default: () => SUGGESTION_QUESTIONS
  }
})

defineEmits(['select-suggestion'])
</script>

<style scoped>
.welcome-screen {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;
  padding: 60px 20px;
}

.welcome-icon {
  width: 80px;
  height: 80px;
  background: var(--primary-gradient);
  border-radius: 24px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 24px;
  box-shadow: var(--shadow-glow);
}

.welcome-icon svg {
  width: 40px;
  height: 40px;
  color: white;
}

.welcome-screen h2 {
  font-size: 1.75rem;
  font-weight: 600;
  margin-bottom: 12px;
  background: var(--primary-gradient);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

.welcome-screen p {
  color: var(--text-secondary);
  max-width: 400px;
  line-height: 1.6;
  margin-bottom: 32px;
}

.suggestion-chips {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 10px;
}

.chip {
  padding: 10px 18px;
  background: var(--bg-card);
  border: 1px solid var(--border-color);
  border-radius: 20px;
  color: var(--text-secondary);
  font-size: 0.875rem;
  cursor: pointer;
  transition: all 0.2s ease;
}

.chip:hover {
  background: var(--bg-card-hover);
  border-color: rgba(102, 126, 234, 0.5);
  color: var(--text-primary);
  transform: translateY(-2px);
}

@media (max-width: 640px) {
  .welcome-screen {
    padding: 40px 16px;
  }

  .welcome-icon {
    width: 64px;
    height: 64px;
  }

  .welcome-icon svg {
    width: 32px;
    height: 32px;
  }

  .welcome-screen h2 {
    font-size: 1.5rem;
  }
}
</style>
