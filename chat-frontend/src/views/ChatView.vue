<template>
  <div class="chat-view">
    <!-- Header -->
    <ChatHeader :is-online="isConnected" @clear="clearMessages" />

    <!-- Messages Area -->
    <main 
      class="messages-area" 
      ref="messagesContainer"
      @scroll="handleScroll"
    >
      <!-- Welcome Screen -->
      <WelcomeScreen 
        v-if="!hasMessages" 
        @select-suggestion="handleSuggestion" 
      />

      <!-- Message List -->
      <MessageBubble
        v-for="msg in messages"
        :key="msg.id"
        :role="msg.role"
        :content="msg.content"
        :time="msg.time"
        :is-streaming="msg.isStreaming"
      />

      <!-- Typing Indicator -->
      <TypingIndicator v-if="isTyping && !currentStreamingMessage" />
    </main>

    <!-- Input Area -->
    <MessageInput
      ref="inputRef"
      v-model="inputValue"
      :loading="isLoading"
      :disabled="isLoading"
      @send="handleSend"
    />
  </div>
</template>

<script setup>
import { ref, watch, onMounted } from 'vue'
import { 
  ChatHeader, 
  MessageBubble, 
  MessageInput, 
  TypingIndicator, 
  WelcomeScreen 
} from '@/components'
import { useChat, useScroll } from '@/composables'

// 使用 Composables
const { 
  messages, 
  isLoading, 
  isTyping, 
  isConnected, 
  currentStreamingMessage,
  hasMessages,
  sendMessage, 
  clearMessages 
} = useChat()

const { 
  containerRef: messagesContainer, 
  scrollToBottom, 
  handleScroll 
} = useScroll()

// 本地状态
const inputValue = ref('')
const inputRef = ref(null)

// 发送消息
const handleSend = async () => {
  const message = inputValue.value.trim()
  if (!message) return
  
  inputValue.value = ''
  await sendMessage(message)
}

// 选择建议问题
const handleSuggestion = (suggestion) => {
  inputValue.value = suggestion
  handleSend()
}

// 监听消息变化，自动滚动到底部
watch(
  () => messages.value.length,
  () => scrollToBottom()
)

// 监听流式消息内容变化
watch(
  () => currentStreamingMessage.value?.content,
  () => scrollToBottom()
)

onMounted(() => {
  inputRef.value?.focus()
})
</script>

<style scoped>
.chat-view {
  display: flex;
  flex-direction: column;
  height: 100vh;
  max-width: 900px;
  margin: 0 auto;
  padding: 0 16px;
}

.messages-area {
  flex: 1;
  overflow-y: auto;
  padding: 24px 0;
}

@media (max-width: 640px) {
  .chat-view {
    padding: 0 12px;
  }
}
</style>
