# Combination

A project for training and testing the new features of Android. This project is split into two editions,
overseas and domestic. Because there are some differences between common libraries. 

## App Module

App Module is mainly about business logic. It uses Dagger 2 to inject fields, and Firebase to
analysis event.

### Fragment's management

On the main page, there are 4 fragments. We choose to hide or show which one.

## Data Module

Data Module is major in data manipulation. It includes getting data from local and network. It uses
Rxjava, retrofit, and room to retrieve data.

## Image Module

The image Module is about to load pics. It uses glide to load pics.

## Player-Lib Module

Player-Lib Module uses Exoplayer to implement player functions.

## Utils Module

Utils Module is a module that includes some global util object.

1. DateUtils
2. LogUtils
3. ToastUtils

## How to link a pull request to an issue

Please refer to this [page](https://docs.github.com/en/issues/tracking-your-work-with-issues/linking-a-pull-request-to-an-issue)


