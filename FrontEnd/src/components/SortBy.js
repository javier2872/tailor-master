import React from "react";

export const SortBy = ({ sortItems }) => {
  return (
    <div id="sort_by" className="form-floating">
      <select
        defaultValue={"none"}
        className="form-select"
        id="floatingSelect"
        aria-label="Floating label select example"
        onChange={(e) => {
          sortItems(e.target.value);
        }}
      >
        <option value="asc">Name(asc)</option>
        <option value="desc">Name(desc)</option>
        <option value="none">None</option>
      </select>
      <label htmlFor="floatingSelect">Ordenar por</label>
    </div>
  );
};
