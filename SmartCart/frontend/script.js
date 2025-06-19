async function fetchCart() {
    const res = await fetch('http://localhost:8080/api/cart');
    const data = await res.json();
    document.getElementById('cart').innerHTML = data.map(item =>
        `<p>${item.quantity} × ${item.name} = ₹${item.quantity * item.price}</p>`).join('');
}

function startVoice() {
    const rec = new webkitSpeechRecognition();
    rec.lang = 'en-US';
    rec.onresult = async (e) => {
        const text = e.results[0][0].transcript;
        const res = await fetch('http://localhost:5000/parse', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ text })
        });
        const items = await res.json();
        for (let name in items) {
            await fetch('http://localhost:8080/api/add', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ name, quantity: items[name] })
            });
        }
        fetchCart();
    };
    rec.start();
}

fetchCart();