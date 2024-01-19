import {differenceInCalendarDays, parseISO} from "date-fns";

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
            throw new Error(`Error creating transaction: ${await response.text()}`);
        }

        return response.json();
    } catch (error) {
        console.error('Error creating transaction:', error);
        throw error;
    }
}

function estimateFee(transaction) {
    if (!transaction.amount || !transaction.transferDate) {
        return 0;
    }

    const amount = parseFloat(transaction.amount);
    const transferDate = parseISO(transaction.transferDate);
    const schedulingDate = new Date();
    const daysBetween = differenceInCalendarDays(transferDate, schedulingDate);

    if (daysBetween === 0) {
        return amount * 0.025 + 3.00;
    } else if (daysBetween >= 1 && daysBetween <= 10) {
        return 12.00;
    } else if (daysBetween >= 11 && daysBetween <= 20) {
        return amount * 0.082;
    } else if (daysBetween >= 21 && daysBetween <= 30) {
        return amount * 0.069;
    } else if (daysBetween >= 31 && daysBetween <= 40) {
        return amount * 0.047;
    } else if (daysBetween >= 41 && daysBetween <= 50) {
        return amount * 0.017;
    } else {
        return 'No applicable fee';
    }
}

export {fetchTransactions, createTransaction, estimateFee};
