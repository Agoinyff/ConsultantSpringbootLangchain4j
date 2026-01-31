<template>
  <footer class="input-area">
    <div class="input-container">
      <div class="input-wrapper">
        <textarea
          ref="textareaRef"
          :value="modelValue"
          @input="onInput"
          @keydown.enter.exact="onEnter"
          :placeholder="placeholder"
          rows="1"
          :disabled="disabled"
        ></textarea>
        <button 
          class="send-btn" 
          @click="onSend" 
          :disabled="!modelValue.trim() || disabled"
          :class="{ active: modelValue.trim() && !disabled }"
        >
          <svg v-if="!loading" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M22 2L11 13" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
            <path d="M22 2L15 22L11 13L2 9L22 2Z" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
          </svg>
          <div v-else class="loading-spinner"></div>
        </button>
      </div>
      <p class="input-hint">{{ hint }}</p>
    </div>
  </footer>
</template>

<script setup>
import { ref, onMounted, nextTick } from 'vue'

const props = defineProps({
  modelValue: {
    type: String,
    default: ''
  },
  placeholder: {
    type: String,
    default: '输入您的问题...'
  },
  hint: {
    type: String,
    default: '按 Enter 发送，Shift + Enter 换行'
  },
  disabled: {
    type: Boolean,
    default: false
  },
  loading: {
    type: Boolean,
    default: false
  }
})

const emit = defineEmits(['update:modelValue', 'send'])

const textareaRef = ref(null)

const adjustHeight = () => {
  const textarea = textareaRef.value
  if (textarea) {
    textarea.style.height = 'auto'
    textarea.style.height = Math.min(textarea.scrollHeight, 120) + 'px'
  }
}

const onInput = (e) => {
  emit('update:modelValue', e.target.value)
  nextTick(adjustHeight)
}

const onEnter = (e) => {
  if (!e.shiftKey) {
    e.preventDefault()
    onSend()
  }
}

const onSend = () => {
  if (props.modelValue.trim() && !props.disabled) {
    emit('send')
  }
}

const focus = () => {
  textareaRef.value?.focus()
}

onMounted(() => {
  focus()
})

defineExpose({ focus, adjustHeight })
</script>

<style scoped>
.input-area {
  padding: 16px 0 24px;
  border-top: 1px solid var(--border-color);
  flex-shrink: 0;
}

.input-container {
  max-width: 100%;
}

.input-wrapper {
  display: flex;
  align-items: flex-end;
  gap: 12px;
  background: var(--bg-card);
  border: 1px solid var(--border-color);
  border-radius: 16px;
  padding: 8px 8px 8px 16px;
  transition: all 0.2s ease;
}

.input-wrapper:focus-within {
  border-color: rgba(102, 126, 234, 0.5);
  box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.1);
}

.input-wrapper textarea {
  flex: 1;
  background: transparent;
  border: none;
  outline: none;
  color: var(--text-primary);
  font-size: 0.9375rem;
  font-family: inherit;
  resize: none;
  line-height: 1.5;
  max-height: 120px;
  padding: 8px 0;
}

.input-wrapper textarea::placeholder {
  color: var(--text-muted);
}

.input-wrapper textarea:disabled {
  cursor: not-allowed;
}

.send-btn {
  width: 44px;
  height: 44px;
  border: none;
  border-radius: 12px;
  background: var(--bg-card);
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: all 0.2s ease;
  flex-shrink: 0;
}

.send-btn:disabled {
  cursor: not-allowed;
  opacity: 0.5;
}

.send-btn.active {
  background: var(--primary-gradient);
  box-shadow: var(--shadow-glow);
}

.send-btn.active:hover {
  transform: scale(1.05);
}

.send-btn svg {
  width: 20px;
  height: 20px;
  color: var(--text-muted);
}

.send-btn.active svg {
  color: white;
}

.loading-spinner {
  width: 20px;
  height: 20px;
  border: 2px solid rgba(255, 255, 255, 0.3);
  border-top-color: white;
  border-radius: 50%;
  animation: spin 0.8s linear infinite;
}

@keyframes spin {
  to {
    transform: rotate(360deg);
  }
}

.input-hint {
  font-size: 0.75rem;
  color: var(--text-muted);
  margin-top: 8px;
  padding: 0 4px;
}
</style>
