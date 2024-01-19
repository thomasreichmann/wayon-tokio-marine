const API_URL = `${import.meta.env.VITE_API_URL}/transactions`;

async function fetchTransactions() {
    try {
        const response = await fetch(`${API_URL}`);
        if (!response.ok) {
            throw new Error('Failed to fetch transactions');
        }
        const data = await response.json();
        return data.content;
    } catch (error) {
        console.error('Error fetching transactions:', error);
        throw error;
    }
}

async function createTransaction(newTransaction) {
    try {
        const response = await fetch(`${API_URL}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newTransaction)
        });

        if (!response.ok) {
            throw new Error('Failed to create transaction');
        }

        return response.json();
    } catch (error) {
        console.error('Error creating transaction:', error);
        throw error;
    }
}

export {fetchTransactions, createTransaction};
