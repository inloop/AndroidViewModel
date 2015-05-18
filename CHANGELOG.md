## 0.3.2(2015-05-18)

Breaking changes:

  - Add setModelView() method which has to be called in every Fragment/Activity after the view was initialized. This is usually on the end of onCreate / onViewCreated - [commit](https://github.com/inloop/AndroidViewModel/commit/54a7d1a96d38d1a17c8bc7c81b081d52064bde28)

## 0.3.1(2015-05-14)

Bugfixes:

  - Fix ViewModel ID clashes after the application was restored due to low memory - [commit](https://github.com/inloop/AndroidViewModel/commit/cecfd54d3008c07c19ad7685b97e9fe2acb5c369)
