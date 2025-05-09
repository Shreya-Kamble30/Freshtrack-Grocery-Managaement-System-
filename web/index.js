document.getElementById('searchInput').addEventListener('keypress', function (event) {
    console.log('Key pressed:', event.key);
        if (event.key === 'Enter') {
            event.preventDefault(); // Prevent the default form submission
            const query = this.value;
            if (query) {
                // Redirect to Search.jsp with the search query as a parameter
                window.location.href = `search?query=${encodeURIComponent(query)}`;
            }
        }
    });
let cartCount = 0; // Initialize a variable to keep track of the cart count

function showToast(message) {
    const toast = document.getElementById('toast');
    toast.textContent = message; // Set the message
    toast.classList.add('show'); // Add the show class

    // Remove the show class after a certain time
    setTimeout(() => {
        toast.classList.remove('show');
    }, 3000); // Adjust the duration as needed
}

function addToCart(product) {
    cartCount++; // Increment the cart count
    updateCartBadge(); // Update the badge display
    showToast(`${product.name} has been added to your cart!`);

    // Send cart item to the backend
    const data = {
        productName: product.name,
        productPrice: product.price,
        quantity: 1 // You can modify this to allow quantity selection
    };

    fetch('/CartServlet', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        console.log('Success:', data);
    })
    .catch((error) => {
        console.error('Error:', error);
    });
}

function updateCartBadge() {
    const badge = document.getElementById('cart-badge');
    badge.textContent = cartCount; // Update the badge text with the current count
    badge.style.display = cartCount > 0 ? 'inline-block' : 'none'; // Show or hide the badge
}

// Event listener for "ADD" buttons
document.querySelectorAll('.add-to-cart').forEach(button => {
    button.addEventListener('click', function() {
        const productCard = this.closest('.product-card');
        const productName = productCard.getAttribute('data-name');
        const productPrice = productCard.getAttribute('data-price');

        const product = {
            name: productName,
            price: parseFloat(productPrice)
        };

        addToCart(product);
    });
});

function fetchCartItems() {
    fetch('/CartServlet')
        .then(response => response.json())
        .then(data => {
            // Handle the data to display in the cart
            console.log(data);
            // You can update the cart display here
        })
        .catch(error => console.error('Error fetching cart items:', error));
}

// Call this function when the cart page loads
document.addEventListener('DOMContentLoaded', fetchCartItems);
