import axios from "axios";

const BASE_URL = "/api/v1/recipes";

export const fetchRecipesByUser = async (userId) =>
  axios.get(`${BASE_URL}/${userId}`);
export const saveRecipe = async (recipe) => axios.post(`${BASE_URL}/`, recipe);
export const editRecipe = async (recipe) =>
  axios.post(`${BASE_URL}/edit`, recipe);
