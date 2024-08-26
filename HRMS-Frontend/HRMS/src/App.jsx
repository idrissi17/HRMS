import React, { useState, useEffect } from "react";
import ListEmployees from "./ListEmployees";
import Table from "./Table";
import Search from "./Search";
import Pagination from "./Pagination";
import axios from "axios";

function App() {
  const [employeesList, setEmployeesList] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState("");
  const [pageNumber, setPageNumber] = useState(0);
  const [pageSize, setPageSize] = useState(10);
  const [totalPages, setTotalPages] = useState(0);
  const [totalElements, setTotalElements] = useState(0);
  const [searchQuery, setSearchQuery] = useState("");
  const [searchType, setSearchType] = useState("");

  useEffect(() => {
    async function fetchEmployees(
      pageNumber,
      pageSize,
      searchQuery,
      searchType
    ) {
      try {
        setIsLoading(true);
        setError("");
        const response = await axios.get(
          searchQuery
            ? `http://localhost:8080/api/employee/all/search?${searchType}=${searchQuery}&pageNumber=${pageNumber}&pageSize=${pageSize}`
            : `http://localhost:8080/api/employee/all?pageNumber=${pageNumber}&pageSize=${pageSize}`
        );

        if (response.status !== 200) throw new Error("Error fetching data");
        setEmployeesList(response.data.content);
        setTotalElements(response.data.totalElements);
        setTotalPages(response.data.totalPages);
      } catch (error) {
        setError(error.message);
      } finally {
        setIsLoading(false);
      }
    }

    fetchEmployees(pageNumber, pageSize, searchQuery, searchType);
  }, [pageNumber, pageSize, searchQuery, searchType]);

  function handleSearch(query) {
    setSearchQuery(query);
    setPageNumber(0);
  }

  function handleSearchTypeChange(e) {
    setSearchType(e.target.value);
  }

  return (
    <>
      <ListEmployees>
        <div className="flex mb-4">
          <select
            value={searchType}
            onChange={handleSearchTypeChange}
            className="mr-1 bg-white-200 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 p-2 transition dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
          >
            <option value="">Select Search Type</option>
            <option value="firstName">Full Name</option>
            <option value="employeeCode">Employee Code</option>
          </select>
          <Search onSearch={handleSearch} searchType={searchType}/>
        </div>
        <Table
          isLoading={isLoading}
          error={error}
          employeesList={employeesList}
        />
        <Pagination
          pageNumber={pageNumber}
          totalPages={totalPages}
          setPageNumber={setPageNumber}
          totalElements={totalElements}
        />
      </ListEmployees>
    </>
  );
}

export default App;
