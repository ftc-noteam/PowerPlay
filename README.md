## koawalib-template
The purpose of this repo is to lower the learning curve of koawalib...
(and make it easier on me when I create new repos lol)  

### Usage
Simply fork this repository to use the standard version of koawalib, published through Jitpack.  

### Local koawalib module
If you want to modify the koawalib source code/have instant updates through git, follow these steps.  
This will setup koawalib as a local repository, and as a dependency to your fork.   
When new updates are pushed to koawalib, the user can choose how/when to upgrade through git.

1. Fork this repository
2. Clone your fork
3. Clone [koawalib](https://github.com/AsianKoala/koawalib) in the same directory
4. cd into the fork
5. Add this template repo as a remote
6. Fetch 
7. Merge with the local-koawalib branch of this repository, allowing unrelated histories
8. Check if koawalib is working as intended
9. Update changes on your fork remote

Here is an example of me following the above steps with my personal repository.

```
git clone git@github.com:ftc-noteam/PP-Public.git
git clone git@github.com:AsianKoala/koawalib.git
cd PP-Public
git remote add template git@github.com:AsianKoala/koawalib-template.git
git fetch --all
git merge template/local-koawalib --allow-unrelated-histories
git add .
git commit -m "initial commit of forked repo"
git push
```
