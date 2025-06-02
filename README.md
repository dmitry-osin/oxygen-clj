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
- **Garden**: CSS generation library for Clojure
- **Buddy**: Authentication and security library

## Project Structure

```
├── src/
│   └── blog/
│       ├── area/
│       │   ├── admin.clj   # Admin interface routes
│       │   └── main.clj    # Main public routes
│       ├── domain/
│       │   ├── db.clj      # Database connection and operations
│       │   ├── post.clj    # Post-related operations
│       │   ├── tag.clj     # Tag-related operations
│       │   └── user.clj    # User-related operations
│       ├── core.clj        # Web server and route handling
│       ├── style.clj       # CSS styles using Garden
│       └── util.clj        # Utility functions
├── resources/
│   ├── db.sql              # Database schema definition
│   └── init-data.sql       # Initial data for the database
└── project.clj             # Project configuration and dependencies
```

## Database Schema

The application uses the following database tables:

- `users`: Store user information
- `posts`: Blog post content and metadata
- `tags`: Categories/tags for posts
- `posts_tags`: Many-to-many relationship between posts and tags

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
(require 'blog.domain.db)
(blog.domain.db/initialize)
```

3. Start the web server:

```
lein repl
(require 'blog.core)
(def server (blog.core/dev-main 3000))
```

4. Visit [http://localhost:3000](http://localhost:3000) in your browser

## API Endpoints

- `/`: Home page
- `/about`: About page
- `/contact`: Contact page
- `/login`: Login page

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

You can use the development server with auto-reload:

```
(def server (blog.core/dev-main 3000))
(.start server)
```

To stop the server:

```
(.stop server)
```

## License

This project is licensed under the [MIT License](LICENSE).

## Contributing

We welcome contributions to improve this Clojure blog project! Here's how you can contribute:

### Getting Started

1. Fork the repository
2. Clone your fork: `git clone https://github.com/your-username/clojure-blog.git`
3. Create a feature branch: `git checkout -b feature/your-feature-name`

### Development Workflow

1. Make your changes
2. Ensure the code follows Clojure style guidelines
3. Add tests for new functionality
4. Verify all tests pass: `lein test`
5. Update documentation as needed

### Pull Request Process

1. Push your changes to your fork
2. Submit a pull request to the main repository
3. Provide a clear description of the changes and their purpose
4. Be responsive to feedback and be prepared to make requested changes

### Code Style

- Follow idiomatic Clojure style
- Use meaningful function and variable names
- Include docstrings for public functions
- Keep functions small and focused on a single responsibility

### Reporting Issues

If you find a bug or have a feature request:

1. Check if the issue already exists in the GitHub issues
2. If not, create a new issue with a clear description and steps to reproduce (for bugs)

### Code of Conduct

- Be respectful and inclusive in all interactions
- Provide constructive feedback
- Help maintain a positive environment for all contributors

We appreciate all contributions, whether they're code improvements, documentation updates, or bug reports!
