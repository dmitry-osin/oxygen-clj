# Clojure Blog

A simple yet powerful blog application built with Clojure, using JDBC for database operations and PostgreSQL as the
database.

## Overview

This project implements a blog platform with basic functionality including:

- User management
- Blog post creation and management
- Tagging system
- Simple web interface using Ring and Hiccup

## Technologies

- **Clojure**: The core programming language
- **Ring**: Web application library for Clojure
- **Hiccup**: HTML generation library for Clojure
- **next.jdbc**: JDBC-based database access library
- **PostgreSQL**: Relational database for data storage

## Project Structure

```
├── src/
│   └── blog/
│       ├── core.clj    # Web server and route handling
│       └── db.clj      # Database connection and operations
├── resources/
│   ├── db.sql          # Database schema definition
│   └── init-data.sql   # Initial data for the database
└── project.clj         # Project configuration and dependencies
```

## Database Schema

The application uses the following database tables:

- `users`: Store user information
- `posts`: Blog post content and metadata
- `tags`: Categories/tags for posts
- `posts_tags`: Many-to-many relationship between posts and tags
- `users_posts`: Many-to-many relationship between users and posts

### Prerequisites

- JDK 8 or higher
- Leiningen
- PostgreSQL

### Database Setup

1. Create a PostgreSQL database named `oxygen`
2. Create a user `oxygen` with password `oxygen`
3. Grant all privileges on the `oxygen` database to the `oxygen` user

### Running the Application

1. Clone the repository
2. Initialize the database:

```
lein repl
(require 'blog.db)
(blog.db/initialize)
```

3. Start the web server:

```
lein repl
(require 'blog.core)
(def server (ring.adapter.jetty/run-jetty #'blog.core/dispatch {:port 3000 :join? false}))
```

4. Visit [http://localhost:3000](http://localhost:3000) in your browser

## API Endpoints

- `/`: Home page
- `/about`: About page
- `/contact`: Contact page

## Development

### Running Tests

```
lein test
```

### REPL Development

The project is configured for interactive development using the REPL:

```
lein repl
```

## License

This project is licensed under the [MIT License](LICENSE).