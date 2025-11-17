import axios, { type InternalAxiosRequestConfig } from "axios";

const API_BASE = import.meta.env.VITE_API_BASE || 'http://localhost:8080/api/v1';

export const publicApi = axios.create({
  baseURL: API_BASE,
  timeout: 10000,
  headers: {
    "Content-Type": "application/json",
    Accept: "application/json"
  },
  withCredentials: true
})

export const privateApi = axios.create({
  baseURL: API_BASE,
  timeout: 10000,
  headers: {
    "Content-Type": "application/json",
    Accept: "application/json"
  },
  withCredentials: true
})

privateApi.interceptors.request.use((config: InternalAxiosRequestConfig) => {
  // if (token && config.headers) {
  //   config.headers["Authorization"] = `Bearer ${token}`;
  // }
  return config;
}, (e) => Promise.reject(e));

privateApi.interceptors.response.use(response => response,
  async (e) => {
    const originalRequest = e.config;

    return Promise.reject(e);
  }
)