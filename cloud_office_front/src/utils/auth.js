import Cookies from 'js-cookie'

const TokenKey = 'vue_admin_template_token'

export function getToken() {
  return Cookies.get(TokenKey)
}

export function setToken(token) {
  return Cookies.set(TokenKey, token)
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}

export function getUsername() {
  return Cookies.get('username')
}

export function setUsername(username) {
  return Cookies.set('username', username)
}

export function removeUsername() {
  return Cookies.remove('username')
}

export function getId() {
  return Cookies.get('id')
}

export function setId(id) {
  return Cookies.set('id', id)
}

export function removeId() {
  return Cookies.remove('id')
}

export function getRole() {
  return Cookies.get('role')
}

export function setRole(role) {
  return Cookies.set('role', role)
}

export function removeRole() {
  return Cookies.remove('role')
}

