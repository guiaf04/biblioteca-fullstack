// Teste simples do frontend (mock)
(async function testFrontend() {
  const form = document.createElement('form');
  form.innerHTML = '<input id="title"><input id="author"><input id="year">';
  document.body.appendChild(form);
  document.getElementById('title').value = 'Livro Teste';
  document.getElementById('author').value = 'Autor Teste';
  document.getElementById('year').value = '2024';
  if (
    document.getElementById('title').value === 'Livro Teste' &&
    document.getElementById('author').value === 'Autor Teste' &&
    document.getElementById('year').value === '2024'
  ) {
    console.log('Teste de preenchimento de formulário: OK');
  } else {
    console.error('Teste de preenchimento de formulário: Falhou');
  }
  document.body.removeChild(form);
})();
