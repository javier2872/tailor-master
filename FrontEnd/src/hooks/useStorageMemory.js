import { useState } from "react";

export function useStorageMemory(keyName, initialValue = null) {
  //inicializamos el hook
  const [value, setValue] = useState(
    JSON.parse(localStorage.getItem(keyName)) || initialValue
  );
  //modifcamos el valor de la key en el storage local
  const handleValueChange = (updatedValue) => {
    localStorage.setItem(keyName, JSON.stringify(updatedValue));
    setValue(updatedValue);
  };

  return [value, handleValueChange];
}
