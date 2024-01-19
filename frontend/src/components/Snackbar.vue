<template>
  <div v-if="visible" class="snackbar" :class="type">
    {{ message }}
  </div>
</template>

<script setup>
import {ref, watch} from 'vue';

const props = defineProps({
  message: String,
  type: String // 'success' or 'error'
});

const visible = ref(false);

watch(() => props.message, (newMessage) => {
  if (newMessage) {
    visible.value = true;
    setTimeout(() => visible.value = false, 3000); // Hide after 3 seconds
  }
});
</script>

<style scoped>
.snackbar {
  position: fixed;
  bottom: 20px;
  left: 50%;
  transform: translateX(-50%);
  padding: 10px 20px;
  color: #fff;
  border-radius: 5px;
  text-align: center;
  z-index: 1000;
  transition: 0.3s ease;
}

.success {
  background-color: green;
}

.error {
  background-color: red;
}
</style>
