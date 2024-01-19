<script setup>
import TransactionForm from "@/components/TransactionForm.vue";
import TransactionTable from "@/components/TransactionTable.vue";
import Snackbar from "@/components/Snackbar.vue";
import {createTransaction as createTx, fetchTransactions} from "@/services/transactionService.js";
import {onMounted, ref} from "vue";

const transactions = ref([]);
const snackbarMessage = ref('');
const snackbarType = ref('');
const snackbarKey = ref(0);

function showSnackbar(message, type) {
  snackbarKey.value++; // Increment the key to force re-render
  snackbarMessage.value = message;
  snackbarType.value = type;
}

async function createTransaction(newTransaction) {
  try {
    await createTx(newTransaction);
    transactions.value = await fetchTransactions();
    showSnackbar('Transaction created successfully', 'success');
  } catch (error) {
    console.error('Error in createTransaction:', error);
    showSnackbar(error.message, 'error');
  }
}

onMounted(async () => {
  try {
    transactions.value = await fetchTransactions();
  } catch (error) {
    console.error('Error on mounted:', error);
    showSnackbar('Error fetching transactions', 'error');
  }
});
</script>


<template>
  <main>
    <TransactionForm @create="createTransaction"/>
    <TransactionTable :transactions="transactions"/>
    <Snackbar :message="snackbarMessage" :type="snackbarType"/>
  </main>
</template>
<style>
* {
  color-scheme: dark;
}

body, html {
  margin: 0;
  padding: 0;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

main {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

  gap: 2em
}
</style>
