# TripScribe
A travel logging(with Images) android mobile application.

This Android application uses **AWS Amplify**, a set of tools and serverless services in the cloud.

The Amplify Android library is AWS' preferred mechanism for interacting with AWS services from an Android device.
The library provides a high-level interface to perform different categories of cloud operations.

Furthermore, using the **Amplify CLI** and libraries this project uses **Amazon Cognito**, a managed user identity provider. The Cognito Hosted User Interface present an entire user **authentication** flow, allowing users to sign up, sign in, and reset their password.

This project also uses **GraphQL API** that leverages AWS AppSync (a managed GraphQL service) which is backed by **Amazon DynamoDB** (a NoSQL database) for the **CRUD+L** (create, read, update, delete, and list) functionality of the application and storage service leveraging **Amazon S3** to enable image uploading, fetching, and rendering in the app.
