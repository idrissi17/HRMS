import React from "react";

function Pagination({ pageNumber, totalPages, setPageNumber, totalElements }) {
  function handlePrevious() {
    if (pageNumber > 0) setPageNumber(pageNumber - 1);
  }
  function handleNext() {
    if (pageNumber < totalPages - 1) setPageNumber(pageNumber + 1);
  }

  const start = pageNumber * 10 + 1;
  const end = Math.min(start + 9, totalElements);

  return (
    <nav
      className="flex flex-col md:flex-row justify-between items-start md:items-center space-y-4 md:space-y-0 p-6 bg-gray-50 dark:bg-gray-700"
      aria-label="Table navigation"
    >
      <span className="text-sm font-normal text-gray-600 dark:text-gray-400">
        Showing
        <span className="font-semibold text-gray-900 dark:text-white mx-1">
          {start}-{end}
        </span>
        of
        <span className="font-semibold text-gray-900 dark:text-white ml-1">
          {totalElements}
        </span>
      </span>
      <ul className="inline-flex items-center -space-x-px">
        <li onClick={handlePrevious}>
          <a
            href="#"
            className={`flex items-center justify-center h-full py-2 px-3 text-sm text-gray-600 bg-gray-200 rounded-l-lg border border-gray-300 hover:bg-gray-300 hover:text-gray-800 transition dark:bg-gray-600 dark:border-gray-500 dark:text-gray-400 dark:hover:bg-gray-500 dark:hover:text-white ${
              pageNumber === 0 ? "cursor-not-allowed opacity-50" : ""
            }`}
          >
            <span className="sr-only">Previous</span>
            <svg
              className="w-5 h-5"
              aria-hidden="true"
              fill="currentColor"
              viewBox="0 0 20 20"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path
                fillRule="evenodd"
                d="M12.707 5.293a1 1 0 010 1.414L9.414 10l3.293 3.293a1 1 0 01-1.414 1.414l-4-4a1 1 0 010-1.414l4-4a1 1 0 011.414 0z"
                clipRule="evenodd"
              />
            </svg>
          </a>
        </li>
        {[...Array(totalPages)].map((_, index) => (
          <li key={index} onClick={() => setPageNumber(index)}>
            <a
              href="#"
              className={`flex items-center justify-center text-sm py-2 px-3 leading-tight border ${
                pageNumber === index
                  ? "text-white bg-blue-600 border-blue-600"
                  : "text-gray-600 bg-gray-200 border-gray-300 hover:bg-gray-300 hover:text-gray-800 dark:bg-gray-600 dark:border-gray-500 dark:text-gray-400 dark:hover:bg-gray-500 dark:hover:text-white"
              } transition`}
            >
              {index + 1}
            </a>
          </li>
        ))}
        <li onClick={handleNext}>
          <a
            href="#"
            className={`flex items-center justify-center h-full py-2 px-3 text-sm text-gray-600 bg-gray-200 rounded-r-lg border border-gray-300 hover:bg-gray-300 hover:text-gray-800 transition dark:bg-gray-600 dark:border-gray-500 dark:text-gray-400 dark:hover:bg-gray-500 dark:hover:text-white ${
              pageNumber === totalPages - 1
                ? "cursor-not-allowed opacity-50"
                : ""
            }`}
          >
            <span className="sr-only">Next</span>
            <svg
              className="w-5 h-5"
              aria-hidden="true"
              fill="currentColor"
              viewBox="0 0 20 20"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path
                fillRule="evenodd"
                d="M7.293 14.707a1 1 0 010-1.414L10.586 10 7.293 6.707a1 1 0 011.414-1.414l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414 0z"
                clipRule="evenodd"
              />
            </svg>
          </a>
        </li>
      </ul>
    </nav>
  );
}

export default Pagination;
