# Node API Skeleton
This is a simple API skeleton for a Node.js/MySQL-based applications.

This is designed for a Mobile Applications course. If you want a more in-depth introduction to backend development, this will not be sufficient and I recommend one of the other million on the internet.

# Recommended Applications
* Get [Postman](https://www.getpostman.com/downloads/).
    * Available on all platforms. Postman allows you to test your API endpoints thoroughly and without a frontend application or a web browser.
* Get [Visual Studio Code](https://code.visualstudio.com).
    * Available on Windows, Mac and Linux, VS Code is a powerful text editor with an integrated terminal and a ton of features for working with Node.js-based servers. Most Node.js developers swear by it, myself and the original creator of this codebase included.
    * Some people also like Atom, Sublime Text, Vim… Figure out what works for you.

# Dependencies
* Git
* MySQL
    * MariaDB is a version of MySQL maintained by the original creator with more features.
* Node.js with NPM

Make sure you uninstall any previous versions of Git, MySQL or Node.js if you follow these instructions.

## Windows
First, we install the [chocolatey](https://chocolatey.org) package manager. This allows you to install dependencies in a similar fashion to *apt-get* on Linux.

To install it, open PowerShell as Administrator, (PLEASE DON'T USE CMD UNDER ANY CIRCUMSTANCE) and then type:
```powershell
Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))
```

Sometimes it may ask you for permissions again. Just type "A" and press enter.

Run these in Powershell as Administrator:
```powershell
choco install -y nodejs-lts mariadb git
```

Then **restart your computer**.

## macOS
First, install the [brew](https://brew.sh) package manager. This allows you to install packages in a dependencies fashion to *apt-get* on Linux.

To install it, just paste this into your Terminal:
```bash
/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
```

After the installation is done:

```bash
brew install mariadb node
# This command launches MySQL
brew services start mariadb
```

## Debian-based Linux (Debian, Ubuntu, Pop!_OS, etc.)
In your terminal:

```bash
sudo apt-get update
sudo apt-get install nodejs npm mariadb-client mariadb-server git
```

That's it!

On some versions of Linux, if the command `node` doesn't work, you may need to do this:

```bash
ln -s /usr/bin/nodejs /usr/bin/node
```

## Other Linux
Chances are, if you"re using another Linux, you know what you"re doing and can probably install these dependencies on your own. :)

# Usage
These steps are applicable for all platforms, including Windows.

Inside your Terminal or Powershell Admin Instance:
## First-time steps
```bash
# Clone the repository
git clone https://github.com/donn/nodejs-api-skeleton
# Go inside
cd nodejs-api-skeleton
# Install package dependencies
npm install
# Setup Database
sudo mysql -u root # Linux/macOS
mysql -u root # Windows
```

Then copy and paste the contents of `Database.sql` into the mysql prompt, press return, then Ctrl+D to exit.

## From now on
```bash
npm run dev
```

You can then visit http://localhost:3000/health in your web browser or GET it via Postman to verify it works.

### Connecting to the database via terminal

```bash
mysql -u root -p password
```

Then write your password (which is "password" by default.)

# File structure
## package.json
This is the project manifest for your backend.

You'll note that I defined two commands: `dev` and `start`.

`dev` uses a library called nodemon, which reloads the app every time there is a change to the files. This is useful during, you guessed it, development! `start` simply runs the app, on the other hand, useful for when you're running on an actual server.

### Packages used
Node.js allows you to install and remove libraries very easily by using the npm package manager (and no, npm does not stand for "node package manager" anymore.)

You will note we're using a couple of those under dependencies:
- express: Express is the most popular HTML server for Node.js.
- body-parser: An extension to express that handles parsing query strings and POST bodies.
- morgan: An extension to express that logs all requests to the console.
- mysql: A library that streamlines connecting to MySQL-based RDBMS.

You'll also note that nodemon is under devDependencies. That is because nodemon is only required when developing the application and not when running it.

## controllers/*.js
Controllers are typically pieces of code and classes that interact with data.

## routes/*.js
These are the API endpoints you are going to create. For now, there is just an index.js, but most serious projects have multiple. The index.js is typically responsible for including other files.

Routes use the controllers to access and process data.

## app.js
This is the entry point of the application. It imports the app's other modules and starts the server that listens on a particular port. It will route calls to API endpoints to routes appropriately.

# License, Acknowledgment
MIT License, see "LICENSE".

Original code Copyright (c) 2019 Ali Khaled

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

