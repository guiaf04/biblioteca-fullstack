const API_URL = 'http://localhost:8080/books';

async function fetchBooks() {
  const res = await fetch(API_URL);
  const books = await res.json();
  renderBooks(books);
}

function renderBooks(books) {
  const tbody = document.getElementById('books-tbody');
  tbody.innerHTML = '';
  books.forEach(book => {
    const tr = document.createElement('tr');
    tr.innerHTML = `
      <td>${book.id}</td>
      <td>${book.title}</td>
      <td>${book.author}</td>
      <td>${book.year}</td>
      <td>
        <button onclick="editBook(${book.id})">Editar</button>
        <button onclick="deleteBook(${book.id})">Excluir</button>
      </td>
    `;
    tbody.appendChild(tr);
  });
}

document.getElementById('book-form').onsubmit = async function(e) {
  e.preventDefault();
  const id = document.getElementById('book-id').value;
  const title = document.getElementById('title').value;
  const author = document.getElementById('author').value;
  const year = parseInt(document.getElementById('year').value);
  const book = { title, author, year };
  if (id) {
    await fetch(`${API_URL}/${id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(book)
    });
  } else {
    await fetch(API_URL, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(book)
    });
  }
  this.reset();
  document.getElementById('cancel-edit').style.display = 'none';
  fetchBooks();
};

window.editBook = async function(id) {
  const res = await fetch(`${API_URL}/${id}`);
  const book = await res.json();
  document.getElementById('book-id').value = book.id;
  document.getElementById('title').value = book.title;
  document.getElementById('author').value = book.author;
  document.getElementById('year').value = book.year;
  document.getElementById('cancel-edit').style.display = '';
};

document.getElementById('cancel-edit').onclick = function() {
  document.getElementById('book-form').reset();
  document.getElementById('cancel-edit').style.display = 'none';
};

window.deleteBook = async function(id) {
  await fetch(`${API_URL}/${id}`, { method: 'DELETE' });
  fetchBooks();
};

fetchBooks();
