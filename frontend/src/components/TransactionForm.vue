<script setup>
import {defineEmits, ref} from 'vue';

const emit = defineEmits(['create']);

const transaction = ref({
  originAccount: '',
  destinationAccount: '',
  amount: null,
  transferDate: ''
});

function submitForm() {
  emit('create', {...transaction.value});
  transaction.value = {originAccount: '', destinationAccount: '', amount: null, date: ''};
}
</script>

<template>
  <form @submit.prevent="submitForm">
    <input v-model="transaction.originAccount" type="text" placeholder="Origin Account" required>
    <input v-model="transaction.destinationAccount" type="text" placeholder="Destination Account" required>
    <input v-model="transaction.amount" type="number" placeholder="Amount" required>
    <input v-model="transaction.transferDate" type="date" placeholder="Date" required>
    <button type="submit">Create Transaction</button>
  </form>
</template>

<style scoped>
form {
  max-width: 500px;
  padding: 20px;
  border: 1px solid #333;
  border-radius: 8px;
  background-color: #202020;
  color: #fff;
  box-shadow: 0 2px 4px rgba(255, 255, 255, 0.1);
}

form input, form button {
  width: calc(100% - 16px);
  padding: 8px;
  margin: 8px 0;
  border: 1px solid #555;
  background-color: #282828;
  color: #fff;
  border-radius: 4px;
  box-sizing: border-box;
}

form input::placeholder {
  color: #aaa;
}

form button {
  background-color: #4CAF50;
  border: none;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

form button:hover {
  background-color: #45a049;
}
</style>
