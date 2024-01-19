<script setup>
import TransactionForm from "@/components/TransactionForm.vue";
import TransactionTable from "@/components/TransactionTable.vue";
import Snackbar from "@/components/Snackbar.vue";
import {createTransaction as createTx, fetchTransactions} from "@/services/transactionService.js";
import {onMounted, ref} from "vue";

const transactions = ref([]);
const currentPage = ref(0);
const pageSize = 10; // You can adjust this as needed
const isLastPage = ref(true)

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
    await loadTransactions(0)
    showSnackbar('Transaction created successfully', 'success');
  } catch (error) {
    console.error('Error in createTransaction:', error);
    showSnackbar(error.message, 'error');
  }
}

async function loadTransactions(page) {
  try {
    const response = await fetchTransactions(page, pageSize);
    transactions.value = response.content; // Assuming your response has a 'content' field
    currentPage.value = response.pageable.pageNumber
    isLastPage.value = response.last
  } catch (error) {
    console.error('Error fetching transactions:', error);
    showSnackbar('Error fetching transactions', 'error');
  }
}

onMounted(async () => loadTransactions(0));
</script>


<template>
  <main>
    <TransactionForm @create="createTransaction"/>
    <TransactionTable :transactions="transactions"/>
    <div class="pagination-controls">
      <button @click="loadTransactions(currentPage - 1)" :disabled="currentPage <= 0">Previous</button>
      <span>Page {{ currentPage + 1 }}</span>
      <button @click="loadTransactions(currentPage + 1)" :disabled="isLastPage">Next</button>
      <!-- Add logic to disable if on last page -->
    </div>
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

.pagination-controls {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}

.pagination-controls button {
  margin: 0 10px;
}
</style>
