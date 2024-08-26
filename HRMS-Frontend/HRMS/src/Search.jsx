import React, { useState } from "react";

function Search({ onSearch, searchType }) {
  const [query, setQuery] = useState("");
  const handleChange = (e) => {
    e.preventDefault();
    setQuery(e.target.value);
    onSearch(e.target.value);
  };
  return (
    <div className="w-full md:w-1/4">
      <form className="flex items-center">
        <label htmlFor="employee-search" className="sr-only">
          Search
        </label>
        <div className="relative w-full">
          <div className="absolute inset-y-0 left-0 flex items-center pl-3 pointer-events-none">
            <svg
              aria-hidden="true"
              className="w-5 h-5 text-gray-500 dark:text-gray-400"
              fill="currentColor"
              viewBox="0 0 20 20"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path
                fillRule="evenodd"
                d="M8 4a4 4 0 100 8 4 4 0 000-8zM2 8a6 6 0 1110.89 3.476l4.817 4.817a1 1 0 01-1.414 1.414l-4.816-4.816A6 6 0 012 8z"
                clipRule="evenodd"
              />
            </svg>
          </div>
          <input
            type="text"
            id="employee-search"
            className="w-80 bg-white-200 border border-gray-300 text-gray-900 text-sm rounded-lg  focus:ring-blue-500 focus:border-blue-500 block pl-10 p-2 transition dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
            placeholder={
              searchType
                ? `Search By ${
                  searchType === "firstName" ? "Full Name" : "Employee Code"
                }`
                : "Search By Full Name Or Employee Code "
            }
            value={query}
            onChange={handleChange}
          />
        </div>
      </form>
    </div>
  );
}

export default Search;
