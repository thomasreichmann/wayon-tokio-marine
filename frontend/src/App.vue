<script setup>
import TransactionForm from "@/components/TransactionForm.vue";
import {onMounted, ref} from "vue";
import TransactionTable from "@/components/TransactionTable.vue";

const transactions = ref([]);

async function fetchTransactions() {
  try {
    const response = await fetch(`${import.meta.env.VITE_API_URL}/transactions`);
    if (!response.ok) {
      throw new Error('Failed to fetch transactions');
    }

    const data = await response.json()

    transactions.value = data.content;
  } catch (error) {
    console.error('Error fetching transactions:', error);
  }
}

async function createTransaction(newTransaction) {
  try {
    const response = await fetch(`${import.meta.env.VITE_API_URL}/transactions`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(newTransaction)
    });

    if (!response.ok) {
      throw new Error('Failed to create transaction');
    }

    await fetchTransactions();
  } catch (error) {
    console.error('Error creating transaction:', error);
  }
}

onMounted(fetchTransactions)
</script>

<template>
  <main>
    <TransactionForm @create="createTransaction"/>
    <TransactionTable :transactions="transactions"/>
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
