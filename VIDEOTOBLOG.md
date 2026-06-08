# Integrating OpenAPI with Spring Boot 4 for Modern APIs and AI

https://www.youtube.com/watch?v=nfIcjkR4KZ8

Building and managing APIs is a core task for developers. In 2026, with the rise of AI and complex systems, having clear and secure APIs is more important than ever. This post explores how the Spring Boot 4 framework and the OpenAPI standard work together. We will look at new tools for developers and how to safely connect your APIs to AI agents. This guide covers important updates, live demonstrations, and best practices shared by Badr Nassar, the creator of the Spring Doc OpenAPI project.

## The Role of OpenAPI Today

OpenAPI is the main standard for REST APIs. It acts as a common language for everyone involved in an API's life. This includes design, publication, and security.

You use OpenAPI to design your APIs in the early stages. You also use it to publish your APIs to users. These users can be front-end developers or other applications. Most important, you need to secure and control these APIs. OpenAPI provides a standard way to do this. It defines normal security protocols and ways to work with webhooks. This is critical when you open your APIs to the outside world.

An OpenAPI document is both machine-readable and human-readable. It describes your API's structure. The current, most used version is OpenAPI 3.1. This version made big improvements. For example, it now allows request bodies in HTTP GET operations. It also simplified how data types are defined using JSON Schema. This makes the API clear and easy to integrate with other tools.

## Spring Boot 4 and API Evolution

Spring Boot 4 was released in late 2025. It brought major improvements for building web APIs. A key focus is on making APIs more strong and easier to manage.

**Important framework upgrades in Spring Boot 4 include:**

*   **API Versioning Support:** The framework now has built-in ways to handle different versions of your API. This was often done with custom code before.
*   **Problem Details (RFC):** This feature is now supported by default. It provides a standard way for your API to report errors.
*   **Better Code Safety:** Features help manage `null` values. This makes your code stronger and less prone to crashes.

These upgrades mean you write less repetitive code. Your APIs also become more consistent and reliable. With Spring Doc OpenAPI, these new framework features are automatically documented in your OpenAPI specification.

## Introducing Spring Doc OpenAPI

Spring Doc OpenAPI is a popular project that connects Spring Boot and the OpenAPI standard. It started in 2019. In recent times, it has seen nearly 300 million downloads per quarter. The project is supported by a large community with over 100 contributors.

Many large companies use it. This is especially true in regulated industries. For example, payment laws in Europe (PSD2 and PSD3) require banks to expose APIs in OpenAPI format. This project helps them meet that need.

**The library supports a wide range of modern development needs:**

*   It works with Java, Kotlin, and Groovy.
*   It supports both reactive and traditional servlet programming modes.
*   It works with GraalVM for creating fast native images.
*   It integrates seamlessly with Spring Security for authorization.
*   It can be used in serverless environments with Spring Cloud Function.
*   It supports generating OpenAPI docs during CI/CD pipeline builds.

This wide support makes it a flexible tool for any Spring Boot project.

## A Modern Developer Experience with Scalar

A big part of working with APIs is testing and exploring them. The Swagger UI has been the standard tool for this. Spring Doc OpenAPI now supports a new, modern alternative called **Scalar**.

Scalar is an open-source user interface for OpenAPI. It is designed with a developer's experience in mind. You can think of it as the next generation of API exploration tools.

**Key benefits of Scalar include:**

*   **Lightweight and Fast:** It loads quickly, even when served from a content delivery network.
*   **Richer Features:** It offers more ways to test APIs and generate code snippets.
*   **Client Code Generation:** You can create SDKs for many programming languages directly from the UI.

Switching to Scalar in your Spring Boot project is simple. You only need to change one dependency in your project's configuration. Once done, all your API endpoints are listed in a clean, modern interface. You can test them easily without leaving your browser.

## Handling API Versioning Strategies

As APIs change over time, you need a way to manage versions. Spring Boot 4 now supports this directly in the framework. Spring Doc OpenAPI automatically documents these versioning strategies in your OpenAPI spec.

There are four common ways to version an API:

1.  **Path Segments:** The version is part of the URL path (e.g., `/api/v1/users`).
2.  **Query Parameters:** The version is passed as a query argument (e.g., `/api/users?version=1`).
3.  **Request Headers:** The version is included in a header (e.g., `X-API-Version: 1`).
4.  **Media Type:** The version is specified in the `Accept` header (e.g., `application/vnd.myapi.v1+json`).

The framework allows you to choose any of these methods. The best practice is to pick one strategy and use it consistently across your entire application. This keeps things simple for the developers using your API. With Spring Doc OpenAPI, you don't need extra configuration. The versioning you set up in Spring Boot 4 is automatically shown in your API documentation.

## Connecting REST APIs to AI Agents Safely

A major trend is exposing business logic to AI agents. Companies have invested in reliable REST APIs. Now they want AI to use that logic without causing problems. The challenge is control. An AI agent might try to perform a dangerous operation, like deleting data.

Spring Doc OpenAPI provides a bridge to solve this. It can automatically inspect your Spring controllers. It then exposes your REST API endpoints as tools for an AI protocol called **MCP** (Model Context Protocol). MCP is a common standard for AI interactions.

**This bridge handles several key tasks safely:**

*   It turns your API operation names into tool names for the AI.
*   It uses your API descriptions as tool descriptions.
*   It carries over your security rules from Spring Security.

**A crucial feature is the concept of "guardrails" or "human in the loop."** By default, the system will flag any API call that changes data (like POST, PUT, or DELETE). The AI agent will be paused. A human must approve the action before it proceeds. This prevents the AI from making unwanted changes. You can customize these rules. You can mark specific safe endpoints that the AI can use without asking for permission.

## Monitoring AI and API Interactions

When AI agents use your APIs, you need to watch what happens. The Spring Doc OpenAPI integration for MCP includes a monitoring dashboard. This dashboard gives you important information.

**You can track:**

*   **Audit Logs:** See every call made by an AI agent.
*   **User Roles:** Know who (or which AI) made the call.
*   **Request and Response Data:** Check the payloads that were sent and received.
*   **Performance:** Monitor the latency of calls between the AI agent and your API.
*   **Token Usage:** See the size of the data and estimated token count. This helps you manage costs and context window limits for AI models.

This visibility is important. It lets you ensure the AI is using your APIs correctly. It also helps you optimize the descriptions of your APIs for AI agents. You can make sure the AI gets the right information without wasting tokens on extra data.

## Final Thoughts

Spring Boot 4 provides a powerful foundation for building modern, secure APIs. When combined with the Spring Doc OpenAPI project, you get a complete solution. You can design, document, version, and secure your APIs effectively.

The move towards AI adds a new layer. The tools shown here let you safely expose your existing business logic to AI agents. You can do this without rewriting your code. You also gain critical safety controls