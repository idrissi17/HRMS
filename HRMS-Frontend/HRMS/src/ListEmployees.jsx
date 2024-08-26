import React from "react";

function ListEmployees({ children }) {
  return (
    <>
      <section className="bg-gray-100 dark:bg-gray-900 p-5 sm:p-8">
        <div className="mx-auto max-w-screen-xl px-6 lg:px-14">
          <div className="bg-white dark:bg-gray-800 shadow-lg sm:rounded-xl overflow-hidden">
            <div className="flex flex-col md:flex-row items-center justify-between space-y-4 md:space-y-0 md:space-x-6 p-6 bg-gray-50 dark:bg-gray-700">
              {children[0]}
            </div>
            <div className="p-6 overflow-x-auto">{children[1]}</div>
            {children[2]}
          </div>
        </div>
      </section>
    </>
  );
}

export default ListEmployees;
