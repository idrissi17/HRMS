import React, { useState, useEffect } from "react";
import Loading from "./Loading";

function Table({ isLoading, error, employeesList }) {
  const [dropdownVisible, setDropdownVisible] = useState(null);

  const toggleDropdown = (id) => {
    setDropdownVisible(dropdownVisible === id ? null : id);
  };

  const handleOutsideClick = (e) => {
    if (!e.target.closest(".dropdown")) {
      setDropdownVisible(null);
    }
  };

  useEffect(() => {
    document.addEventListener("click", handleOutsideClick);
    return () => {
      document.removeEventListener("click", handleOutsideClick);
    };
  }, []);

  return (
    <div className="p-6">
      <div className="flex flex-col">
        <div className="-m-1.5 overflow-x-auto">
          {isLoading ? (
            <Loading />
          ) : error ? (
            <div className="error-message">
              <p>Error: {error}</p>
            </div>
          ) : (
            <table className="min-w-full divide-y divide-gray-200 dark:divide-neutral-700">
              <thead>
                <tr>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider dark:text-gray-400">
                    Employee Code
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider dark:text-gray-400">
                    Full Name
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider dark:text-gray-400">
                    Email
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider dark:text-gray-400">
                    Address
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider dark:text-gray-400">
                    Phone Number
                  </th>
                  <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider dark:text-gray-400">
                    Actions
                  </th>
                </tr>
              </thead>
              <tbody className="divide-y divide-gray-200 dark:divide-neutral-700">
                {employeesList.map((employee) => (
                  <tr
                    key={employee.id || employee.employeeId}
                    className="hover:bg-gray-100 dark:hover:bg-neutral-700"
                  >
                    <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-800 dark:text-neutral-200">
                      {employee.employeeCode}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-800 dark:text-neutral-200">
                      {`${employee.firstName} ${employee.lastName}`}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-800 dark:text-neutral-200">
                      {employee.email}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-800 dark:text-neutral-200">
                      {employee.address}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-800 dark:text-neutral-200">
                      {employee.phoneNumber}
                    </td>
                    <td className="px-4 py-3 flex items-center justify-end">
                      <button
                        onClick={(e) => {
                          e.stopPropagation(); 
                          toggleDropdown(employee.id || employee.employeeId);
                        }}
                        className="inline-flex items-center p-0.5 text-sm font-medium text-center text-gray-500 hover:text-gray-800 rounded-lg focus:outline-none dark:text-gray-400 dark:hover:text-gray-100"
                        type="button"
                      >
                        <svg
                          className="w-5 h-5"
                          aria-hidden="true"
                          fill="currentColor"
                          viewBox="0 0 20 20"
                          xmlns="http://www.w3.org/2000/svg"
                        >
                          <path d="M6 10a2 2 0 11-4 0 2 2 0 014 0zM12 10a2 2 0 11-4 0 2 2 0 014 0zM16 12a2 2 0 100-4 2 2 0 000 4z" />
                        </svg>
                      </button>
                      {dropdownVisible ===
                        (employee.id || employee.employeeId) && (
                        <div className="absolute z-10 w-48 bg-white rounded-lg shadow-lg dark:bg-gray-800 dropdown">
                          <ul className="py-2 text-sm text-gray-800 dark:text-gray-200">
                            <li>
                              <a
                                href="#"
                                className="block px-4 py-2 hover:bg-blue-100 dark:hover:bg-blue-600 dark:hover:text-white rounded-t-lg"
                              >
                                Show
                              </a>
                            </li>
                            <li>
                              <a
                                href="#"
                                className="block px-4 py-2 hover:bg-blue-100 dark:hover:bg-blue-600 dark:hover:text-white"
                              >
                                Edit
                              </a>
                            </li>
                          </ul>
                          <div className="border-t border-gray-100 dark:border-gray-600"></div>
                          <div className="py-2">
                            <a
                              href="#"
                              className="block px-4 py-2 text-sm text-red-600 hover:bg-red-100 dark:hover:bg-red-600 dark:text-red-400 dark:hover:text-white rounded-b-lg"
                            >
                              Delete
                            </a>
                          </div>
                        </div>
                      )}
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          )}
        </div>
      </div>
    </div>
  );
}

export default Table;
