async function authHeader() {
  const user = prompt("Username (admin/manager/operator):", "admin");
  const pass = prompt("Password:", "admin123");
  const token = btoa(`${user}:${pass}`);
  return { 'Authorization': `Basic ${token}` };
}

async function upload(url, inputId){
  const headers = await authHeader();
  const fileInput = document.getElementById(inputId);
  if (!fileInput.files.length){ alert('Choose a file'); return; }
  const form = new FormData();
  form.append('file', fileInput.files[0]);
  const res = await fetch(url, { method: 'POST', headers, body: form });
  const text = await res.text();
  document.getElementById('out').textContent = text;
}

async function listOrders(){
  const headers = await authHeader();
  const res = await fetch('/api/orders', { headers });
  const json = await res.json();
  document.getElementById('out').textContent = JSON.stringify(json, null, 2);
}
