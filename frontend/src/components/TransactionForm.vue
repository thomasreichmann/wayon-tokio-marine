<script setup>
import {defineEmits, ref, watch} from 'vue';
import {estimateFee} from "@/services/transactionService.js";

const emit = defineEmits(['create']);

const transaction = ref({
  originAccount: '',
  destinationAccount: '',
  amount: null,
  transferDate: ''
});

const estimatedFee = ref("0");

// Watch for changes in transaction.amount and transaction.transferDate
watch(() => [transaction.value.amount, transaction.value.transferDate], () => {
  if (transaction.value.amount && transaction.value.transferDate) {
    estimatedFee.value = estimateFee(transaction.value);
  } else {
    estimatedFee.value = "0";
  }
}, {immediate: true});

function submitForm() {
  emit('create', {...transaction.value});
  transaction.value = {originAccount: '', destinationAccount: '', amount: null, date: ''};
}
</script>

<template>
  <form @submit.prevent="submitForm">
    <input v-model="transaction.originAccount" type="text" placeholder="Origin Account" required>
    <input v-model="transaction.destinationAccount" type="text" placeholder="Destination Account" required>
    <div id="estimated-fee-container">
      <input v-model="transaction.amount" type="number" placeholder="Amount" required>
      <span>Estimated fee: R$ {{ estimatedFee }}</span>
    </div>
    <input v-model="transaction.transferDate" type="date" placeholder="Date" required>
    <button type="submit">Create Transaction</button>
  </form>
</template>

<style scoped>
#estimated-fee-container {
  display: flex;
  align-items: center;
  gap: 24px;
  width: calc(100% - 16px);

  input {
    flex-grow: 1;
  }

  span {
    flex-shrink: 0;
  }
}

form {
  display: flex;
  flex-direction: column;
  align-items: center;

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
